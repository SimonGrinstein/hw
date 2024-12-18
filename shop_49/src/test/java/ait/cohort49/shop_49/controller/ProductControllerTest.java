package ait.cohort49.shop_49.controller;

import ait.cohort49.shop_49.model.dto.ProductDTO;
import ait.cohort49.shop_49.model.entity.Role;
import ait.cohort49.shop_49.model.entity.User;
import ait.cohort49.shop_49.repository.RoleRepository;
import ait.cohort49.shop_49.repository.UserRepository;
import ait.cohort49.shop_49.security.dto.LoginRequestDTO;
import ait.cohort49.shop_49.security.dto.TokenResponseDTO;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductControllerTest {


    @LocalServerPort
    private int port;

    private TestRestTemplate template;
    private HttpHeaders headers;

    private ProductDTO testProduct;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private String adminAccessToken;
    private String userAccessToken;


    private static final String TEST_PRODUCT_TITLE = "Test Product";
    private static final int TEST_PRODUCT_PRICE = 777;
    private static final String TEST_ADMIN_NAME = "Test Admin";
    private static final String TEST_USER_NAME = "Test User";
    private static final String TEST_PASSWORD = "Test password";
    private static final String ROLE_ADMIN_TITLE = "ROLE_ADMIN";
    private static final String ROLE_USER_TITLE = "ROLE_USER";

    private static final String URL_PREFIX = "http://localhost:";
    private static final String AUTH_RESOURCE = "/api/auth";
    private static final String PRODUCTS_RESOURCE = "/api/products";
    private static final String LOGIN_ENDPOINT = "/login";

    private static final String BEARER_PREFIX = "Bearer ";


    @BeforeEach
    public void setUp(){
        // Инициализирую TestRestTemplate
        template = new TestRestTemplate();

        headers = new HttpHeaders();

        // Создаем тестовый продукт
        testProduct = new ProductDTO();
        testProduct.setTitle(TEST_PRODUCT_TITLE);
        testProduct.setPrice(new BigDecimal(TEST_PRODUCT_PRICE));

        Role roleAdmin;
        Role roleUser = null;

        User admin = userRepository.findByUsername(TEST_ADMIN_NAME).orElse(null);
        User user = userRepository.findByUsername(TEST_USER_NAME).orElse(null);

        if (admin == null) {
            // Нужно создать тестового админа и сохранить в базу
            roleAdmin = roleRepository.findByTitle(ROLE_ADMIN_TITLE)
                    .orElseThrow(() -> new RuntimeException("Role ADMIN not found in the database"));
            roleUser = roleRepository.findByTitle(ROLE_USER_TITLE)
                    .orElseThrow(() -> new RuntimeException("Role USER not found in the database"));

            admin = new User();
            admin.setUsername(TEST_ADMIN_NAME);
            admin.setPassword(encoder.encode(TEST_PASSWORD));
            admin.setRoles(Set.of(roleAdmin, roleUser));

            userRepository.save(admin);
        }

        if (user == null) {
            // Создать тестового юзера и сохранить
            roleUser = (roleUser == null) ? roleRepository.findByTitle(ROLE_USER_TITLE)
                    .orElseThrow(() -> new RuntimeException("Role USER not found in the database")) : roleUser;

            user = new User();
            user.setUsername(TEST_USER_NAME);
            user.setPassword(encoder.encode(TEST_PASSWORD));
            user.setRoles(Set.of(roleUser));

            userRepository.save(user);

        }

        // POST http://localhost:88888/api/auth/login

        // Создадим ВЕЩ для логина
        LoginRequestDTO loginAdminDto = new LoginRequestDTO(TEST_ADMIN_NAME, TEST_PASSWORD);
        LoginRequestDTO loginUserDto = new LoginRequestDTO(TEST_USER_NAME, TEST_PASSWORD);

        String authUrl = URL_PREFIX + port + AUTH_RESOURCE + LOGIN_ENDPOINT;

        HttpEntity<LoginRequestDTO> request = new HttpEntity<>(loginAdminDto);

        ResponseEntity<TokenResponseDTO> response = template.exchange(
                authUrl,
                HttpMethod.POST,
                request,
                TokenResponseDTO.class
        );

        assertTrue(response.hasBody(), "Authorization response body is empty");

        // Header
        // Authorization: Bearer 65756765567
        TokenResponseDTO tokenResponse = response.getBody();
        adminAccessToken = BEARER_PREFIX + tokenResponse.getAccessToken();



        //! ---- HW
        ResponseEntity<TokenResponseDTO> responseUser = template.exchange(
                authUrl,
                HttpMethod.POST,
                request,
                TokenResponseDTO.class
        );
        assertTrue(responseUser.hasBody(), "Authorization response User body is empty");

        TokenResponseDTO tokenResponseUser = responseUser.getBody();

        assert tokenResponseUser != null;
        userAccessToken = BEARER_PREFIX + tokenResponseUser.getAccessToken();


    }





    @Test
    public void test(){
        // Пустой
    }



}