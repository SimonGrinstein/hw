package ait.cohort49.shop_49.controller;

import ait.cohort49.shop_49.exceptionHandling.Response;
import ait.cohort49.shop_49.exceptionHandling.exception.FirstTestException;
import ait.cohort49.shop_49.model.dto.ProductDTO;
import ait.cohort49.shop_49.service.interfaces.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Parameter;

import java.math.BigDecimal;
import java.util.List;

//http://localhost:8080/products
//    //api/products

@RestController
@RequestMapping("/products")
@Tag(name = "Product controller", description = "Controller for  Products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //POST -> /products
    @Operation(summary = "Create product", description = "Add new product", tags ={"Product"})
    @PostMapping
    public ProductDTO saveProduct(@Valid @RequestBody ProductDTO productDTO) {
        return productService.save(productDTO);
    }

    // GET /products/55
    @Operation(summary = "Get product by id",  tags = {"Product"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDTO.class)),
                    @Content(mediaType = "application/xml", schema = @Schema(implementation = ProductDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Product not found", content = @Content) })
    @GetMapping("/{productId}")
    public ProductDTO getById(
            @Parameter(description = "The id that needs to be fetched", required = true)
            @PathVariable("productId")
            Long id) {

        return productService.findById(id);
    }

    //GET /products
    @GetMapping
    public List<ProductDTO> getAll() {
        return productService.findAllActiveProducts();
    }

    @GetMapping("/count")
    public Long getProductCount() {
        return productService.getProductCount();
    }

    @GetMapping("/total_price")
    public BigDecimal getTotalPrice() {
        return productService.getTotalPrice();
    }

    @GetMapping("/average_price")
    public BigDecimal getAveragePrice() {
        return productService.getAveragePrice();
    }

    @PutMapping ("/restore{id}")
    public ProductDTO restoreProduct(@PathVariable Long id) {
        return productService.restoreProductById(id);
    }

    //delete DELETE -> /product/id
    @DeleteMapping("/{id}")
    public ProductDTO remove(@PathVariable Long id) {
        return productService.deleteById(id);
    }

    //delete DELETE -> /product/id
    @DeleteMapping("/by_title/{name}")
    public ProductDTO remove(@PathVariable String name) {
        return productService.deleteByName(name);
    }

    @ExceptionHandler(FirstTestException.class)
    public ResponseEntity<Response> handleException(FirstTestException exception) {
        Response response = new Response(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


}
