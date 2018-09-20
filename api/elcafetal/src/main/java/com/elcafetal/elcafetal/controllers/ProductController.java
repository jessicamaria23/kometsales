package com.elcafetal.elcafetal.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import com.elcafetal.elcafetal.models.entity.Product;
import com.elcafetal.elcafetal.models.service.ProductService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/products")
	public List<Product> getProducts() {
		return productService.getProducts();
	}

	@GetMapping("/products/{productId}")
	public Product getProduct(@PathVariable int productId) {
		Product product = productService.getProduct(productId);
		return product;
	}

	@PostMapping("/products")
	@ResponseStatus(HttpStatus.CREATED)
	public Product addProduct(@RequestBody Product product) {
		product.setId(null);
		productService.saveProduct(product);
		return product;
	}
	
	@PutMapping("/products")
	@ResponseStatus(HttpStatus.CREATED)
	public Product updateProduct(@RequestBody Product product) {
		productService.saveProduct(product);
		return product;
	}
	
	@DeleteMapping("/products/{productId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteProduct(@PathVariable int productId) {
		productService.deleteProduct(productId);
	}
	
	@PostMapping("/products/fileUpload")
	public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) throws SAXException, InvalidFormatException {
		String message = "";
		try {
			InputStream inputStreamExcel = file.getInputStream();
			InputStream template = ProductService.class.getResourceAsStream("/templates/excel/upload_products_template.xml");
			productService.processExcelFile(template, inputStreamExcel);
			
			return ResponseEntity.status(HttpStatus.OK).body(message);
		} catch (IOException e) {
			message = "Fallo la carga del archivo " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
		}
	}
}