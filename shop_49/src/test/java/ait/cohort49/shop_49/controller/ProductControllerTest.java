package ait.cohort49.shop_49.controller;

import ait.cohort49.shop_49.model.dto.ProductDTO;
import ait.cohort49.shop_49.model.entity.Role;
import ait.cohort49.shop_49.model.entity.User;
import ait.cohort49.shop_49.repository.ProductRepository;
import ait.cohort49.shop_49.repository.RoleRepository;
import ait.cohort49.shop_49.repository.UserRepository;
import ait.cohort49.shop_49.security.dto.LoginRequestDTO;
import ait.cohort49.shop_49.security.dto.TokenResponseDTO;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;
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

    @Autowired
    private ProductRepository productRepository;

    private String adminAccessToken;
    private String userAccessToken;

    private static Long testProductId;


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
    private static final String AUTH_HEADER_NAME = "Authorization";


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


        request = new HttpEntity<>(loginUserDto);

        response = template.exchange(
                authUrl,
                HttpMethod.POST,
                request,
                TokenResponseDTO.class
        );

        assertTrue(response.hasBody(), "Authorization response body is empty");

        tokenResponse = response.getBody();
        userAccessToken = BEARER_PREFIX + tokenResponse.getAccessToken();


    }



    @Test
    public void positiveGettingAllProductsWithoutAuthorization(){
        // GET http:// localhost:88888/api/products
        String url = URL_PREFIX + port + PRODUCTS_RESOURCE;

        //Void -Pustoj klass
        HttpEntity<Void> request = new HttpEntity<>(headers);
        ResponseEntity<List<ProductDTO>> response = template.exchange(
                url,
                HttpMethod.GET,
                request,
                new ParameterizedTypeReference<List<ProductDTO>>(){}
        );

        //Proverka Statusa otveta
        assertEquals(HttpStatus.OK, response.getStatusCode(), "Response has unexpected status");
        //Proveka nalichija tela
        assertTrue(response.hasBody(), "Response has no body");

    }

    @Test
    public void negativeSavingProductWithoutAuthorization(){

        // GET http:// localhost:88888/api/products + body
        String url = URL_PREFIX + port + PRODUCTS_RESOURCE;
        HttpEntity<ProductDTO> request = new HttpEntity<>(testProduct, headers);

        ResponseEntity<ProductDTO> response = template.exchange(
                url,
                HttpMethod.POST,
                request,
                ProductDTO.class
        );

        //Proverka otveta ----- 403 Forbidden
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode(), "Response has unexpected status");

        assertFalse(response.hasBody(), "Response has unexpected body");

    }

    @Test
    public void negativeSavingProductWithUserTokenAuthorization() {

        String url = URL_PREFIX + port + PRODUCTS_RESOURCE;
        headers.put(AUTH_HEADER_NAME, List.of(userAccessToken));
        //Sostavitj zapros peredatj TestProduct

        //zhdem 403 otvet


    }

    @Test
    @Order(10)
    public void positiveSavingProductWithAdminTokenAuthorization() {

        String url = URL_PREFIX + port+ PRODUCTS_RESOURCE;
        headers.put(AUTH_HEADER_NAME, List.of(adminAccessToken));

        HttpEntity<ProductDTO> request = new HttpEntity<>(testProduct, headers);

        ResponseEntity<ProductDTO> response = template.exchange(
                url,
                HttpMethod.POST,
                request,
                ProductDTO.class
        );

        // Повторное сохранение продукта с таким же именем невозможно
        // Последний в Order позитивный - получит продукт по ID.
        // После проверок - удалить продукт из БД по id
        assertEquals(HttpStatus.OK, response.getStatusCode(), "Response has unexpected status");
        assertTrue(response.hasBody(), "Response body is empty");

        ProductDTO savedProduct = response.getBody();
        assertNotNull(savedProduct, "Response body doesn't have a product");
        assertEquals(testProduct.getTitle(), savedProduct.getTitle(), "Saved product has unexpected title");

        testProductId = savedProduct.getId();
    }


    @Test
    @Order(20)
    void negativeGettingProductByIdWithoutAuthorization(){

        // GET http://localhost:88888/api/products/777

        String url = URL_PREFIX + port+ PRODUCTS_RESOURCE + "/" + testProductId;
        //TODO
        //Сформировать запрос, без добавления заголовка авторизации
        // Отправить запрос, получить ответ
        // Проверить статус 403 / 401, проверить отсутствие тела
    }


    @Test
    @Order(30)
    void negativeGettingProductByIdWithBasicAuthorization(){
        // GET http://localhost:88888/api/products/777

        String url = URL_PREFIX + port+ PRODUCTS_RESOURCE + "/" + testProductId;


        HttpEntity<Void> request = new HttpEntity<>(headers);

        ResponseEntity<ProductDTO> response = template
                .withBasicAuth(TEST_USER_NAME, TEST_PASSWORD)
                .exchange(
                        url,
                        HttpMethod.GET,
                        request,
                        ProductDTO.class
                );

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode(), "Response has unexpected status");
        assertFalse(response.hasBody(), "Response has unexpected body");

    }

    @Test
    @Order(40)
    void negativeGettingProductByIdWithIncorrectTokenAuthorization(){
        String url = URL_PREFIX + port+ PRODUCTS_RESOURCE + "/" + testProductId;

        headers.put(AUTH_HEADER_NAME, List.of(adminAccessToken + "a"));

        // TODO запрос, ответ, проверка статуса 403
    }

    @Test
    @Order(50)
    void positiveGettingProductByIdWithAdminTokenAuthorization(){
        String url = URL_PREFIX + port+ PRODUCTS_RESOURCE + "/" + testProductId;
        headers.put(AUTH_HEADER_NAME, List.of(adminAccessToken));

        HttpEntity<Void> request = new HttpEntity<>(headers);

        ResponseEntity<ProductDTO> response = template.exchange(url, HttpMethod.GET, request, ProductDTO.class);

        assertEquals(HttpStatus.OK, response.getStatusCode(), "Response has unexpected status");

        ProductDTO savedProduct = response.getBody();
        assertNotNull(savedProduct, "Response body doesn't have a product");

        // В последнем тесте цепочки удаляем сохраненный продукт из БД
        // Чтобы при следующем прогоне тестов метод сохранения продукты отрабатывал и мы получали его id

        productRepository.deleteById(savedProduct.getId());
    }



}