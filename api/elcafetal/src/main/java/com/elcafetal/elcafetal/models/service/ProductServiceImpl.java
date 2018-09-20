package com.elcafetal.elcafetal.models.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.converters.StringConverter;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.jxls.reader.ReaderBuilder;
import org.jxls.reader.XLSReadStatus;
import org.jxls.reader.XLSReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xml.sax.SAXException;

import com.elcafetal.elcafetal.models.dao.ProductDAO;
import com.elcafetal.elcafetal.models.entity.Product;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDAO productDAO;

	@Override
	@Transactional(readOnly=true)
	public List<Product> getProducts() {
		return (List<Product>) productDAO.findAll();
	}

	@Override
	@Transactional
	public void saveProduct(Product product) {
		productDAO.save(product);
	}

	@Override
	@Transactional(readOnly=true)
	public Product getProduct(Integer id) {
		return productDAO.findById(id).get();
	}

	@Override
	@Transactional
	public void deleteProduct(Integer id) {
		productDAO.deleteById(id);
	}
	
	@Override
	public List<Product> getProductListExcel(InputStream template, InputStream excel) throws IOException, SAXException, InvalidFormatException {
		List<Product> products = new ArrayList<>();
		
		Map<String, Object> beans = new HashMap<>();
		beans.put("products", products);
					 
		final XLSReader xlsReader = ReaderBuilder.buildFromXML(template);
		xlsReader.getConvertUtilsBeanProvider().getConvertUtilsBean().register(new StringConverter(), java.lang.String.class);
		XLSReadStatus readStatus = xlsReader.read(excel, beans);
		if (!readStatus.isStatusOK()) {
			throw new RuntimeException("No se puede leer el archivo.");
		}
			
		return products;
	}

	@Override
	public void processExcelFile(InputStream template, InputStream inputStreamExcel) throws IOException, SAXException, InvalidFormatException {
		List<Product> listProducts = getProductListExcel(template, inputStreamExcel);
		if (listProducts != null) {
			createProducts(listProducts);
		}		
	}
	
	@Transactional
	private void createProducts(List<Product> listProducts) {
		for (Product product : listProducts) {
			productDAO.save(product);
		}	
	}
}