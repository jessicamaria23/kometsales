package com.elcafetal.elcafetal.models.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.xml.sax.SAXException;

import com.elcafetal.elcafetal.models.entity.Product;

public interface ProductService {
	
	/**
	 * Lista la informacion de los productos del cafetal.
	 * @return
	 */
	public List<Product> getProducts();

	/**
	 * Guarda la informacion de un producto del cafetal.
	 * @param product
	 */
	public void saveProduct(Product product);
	
	/**
	 * Obtiene la informacion de un producto por id.
	 * @param theId
	 * @return
	 */
	public Product getProduct(Integer id);
	
	/**
	 * Elimina la informacion de un producto por id.
	 * @param id
	 */
	public void deleteProduct(Integer id);

	/**
	 * Obtiene la informacion del excel cargado.
	 * @param template
	 * @param inputStreamExcel
	 * @return
	 * @throws IOException
	 * @throws SAXException
	 * @throws InvalidFormatException 
	 */
	public List<Product> getProductListExcel(InputStream template, InputStream inputStreamExcel) throws IOException, SAXException, InvalidFormatException ;

	public void processExcelFile(InputStream template, InputStream inputStreamExcel) throws IOException, SAXException, InvalidFormatException;
}