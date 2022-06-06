package com.vnpay.initdata;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vnpay.role.entity.Role;
import com.vnpay.role.repository.RoleRepository;
import com.vnpay.user.dto.RequestUserDto;
import com.vnpay.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ResourceLoader;
import org.springframework.transaction.annotation.Transactional;




@Configuration
public class InitData {

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    private static final String DATA_JSON = "user_data.json";

    private static final String ROLE_DATA_JSON = "role_data.json";

    @EventListener(ApplicationReadyEvent.class)
    @Transactional(rollbackFor = IOException.class)
    public void doSomethingAfterStartup() throws IOException {
        initDataTicketGroupPermission();
    }

    private void initDataTicketGroupPermission() {
        ObjectMapper objectMapper = new ObjectMapper();
        List<RequestUserDto> listUserFromJson;
        List<Role> listRoleFromJson;
        try {
            listRoleFromJson = Arrays.asList(objectMapper.readValue(new URL("file:src/main/resources/data.init/" + ROLE_DATA_JSON),
                    Role[].class));
            List<Role> collect  = listRoleFromJson.stream().filter(role -> !(roleRepository.findByName(role.getName()).isPresent()))
                    .collect(Collectors.toList());
            roleRepository.saveAll(collect);

            listUserFromJson = Arrays.asList(objectMapper.readValue(new URL("file:src/main/resources/data.init/" + DATA_JSON),
                    RequestUserDto[].class));
            listUserFromJson.stream().forEach(requestUserDto -> userService.saveUser(requestUserDto));

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
