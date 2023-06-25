package pro.sky.socks_warehouse_app.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pro.sky.socks_warehouse_app.dto.SockDTO;
import pro.sky.socks_warehouse_app.entity.Sock;
import pro.sky.socks_warehouse_app.exception.LessThanZeroException;
import pro.sky.socks_warehouse_app.exception.NotFoundException;
import pro.sky.socks_warehouse_app.exception.OperationException;
import pro.sky.socks_warehouse_app.exception.SockNotFoundException;
import pro.sky.socks_warehouse_app.mapper.SockMapper;
import pro.sky.socks_warehouse_app.repository.SockRepository;
import pro.sky.socks_warehouse_app.service.SockService;
import java.security.InvalidParameterException;

@Slf4j
@Service
@RequiredArgsConstructor
public class SockServiceImpl implements SockService {

    private final SockRepository sockRepository;
    private final SockMapper mapper;

    /**
     * Метод для приема носков на склад
     *
     * @param sockDTO обьект для хранания параметров приема носков
     */

    public void addSock(SockDTO sockDTO) {
        log.info("Сохраняет партию носков в базу данных");
        if (!sockDTO.getColor().isEmpty() && sockDTO.getCottonPart() > 0 && sockDTO.getCottonPart() <= 100
                && sockDTO.getQuantity() > 0) {
            Sock sock = sockRepository.findByColorAndCottonPart(sockDTO.getColor().toLowerCase(), sockDTO.getCottonPart());
            if (!sockRepository.findAll().contains(sock)) {
                sockRepository.save(mapper.sockDtoToSock(sockDTO));
                log.info("{} Сохранен в базе данных", sock);
            } else {
                int quantity = sock.getQuantity() + sockDTO.getQuantity();
                sock.setQuantity(quantity);
                sockRepository.save(sock);
                log.info("{} Изменен в базе данных", sock);
            }
        } else {
            log.error("Неверный запрос");
            throw new OperationException();
        }
    }

    /**
     * Метод для отправки носков со склада
     * @param sockDTO обьект для хранания параметров отправки носков
     */

    @Override
    @Transactional
    public void deleteSock(SockDTO sockDTO) {
        log.info("Уменьшает количество носков в базе данных");
        if (!sockDTO.getColor().isEmpty() && sockDTO.getCottonPart() > 0 && sockDTO.getCottonPart() <= 100 && sockDTO.getQuantity() > 0) {
            Sock sock = sockRepository.findByColorAndCottonPart(sockDTO.getColor().toLowerCase(), sockDTO.getCottonPart());
            int quantity = sock.getQuantity() - sockDTO.getQuantity();
            if (quantity < 0) {
                log.debug("На складе нет такого колличества носков");
                throw new LessThanZeroException();
            } else if (quantity == 0) {
                sockRepository.deleteById(sock.getId());
                log.info("{} Удален из базы данных", sock);
            } else {
                sock.setQuantity(quantity);
                sockRepository.save(sock);
                log.info("{} Изменен в базе данных", sock);
            }
        } else {
            log.error("Неверный запрос");
            throw new OperationException();
        }
    }


    /**
     * Метод находит общее колличество носков на складе
     *
     * @param color      цвет носков
     * @param operation  операция сравнения носков
     * @param cottonPart процент хлопка в составе носков
     * @return возращает обьект типа Integer
     */

    @Override
    @Transactional

    public Integer getQuantity(String color, String operation, Integer cottonPart) {
        log.info("Возвращяет общее колличество носков по указанным параметрам");
        if (cottonPart < 0 || cottonPart > 100) throw new InvalidParameterException();
        switch (operation) {
            case "moreThan" -> {
                Integer count = sockRepository.findByCottonPartMoreThan(color.toLowerCase(), cottonPart);
                if (count == null) throw new SockNotFoundException();
                else {
                    return checkingSockCount(count);
                }
            }
            case "lessThan" -> {
                Integer count = sockRepository.findByCottonPartLessThan(color.toLowerCase(), cottonPart);
                if (count == null) throw new SockNotFoundException();
                else {
                    return checkingSockCount(count);
                }
            }
            case "equal" -> {
                Integer count = sockRepository.findByCottonPartEquals(color.toLowerCase(), cottonPart);
                if (count == null) throw new SockNotFoundException();
                else {
                    return checkingSockCount(count);
                }
            }
            default -> throw new OperationException();
        }
    }

    /**
     * Метод проверки носков на складе
     *
     * @param count колличество носков
     * @return возвращает обьект типа Integer
     */
    private Integer checkingSockCount(Integer count) {
        if (count != null)
            return count;
        else {
            log.debug("На складе нет носков с такими параметрами");
            throw new NotFoundException();
        }
    }
}


