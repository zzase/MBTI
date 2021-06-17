package com.ykck.mbti.controller;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ykck.mbti.dto.Product;
import com.ykck.mbti.service.ProductService;

@Controller
@RequestMapping("/mbti")
public class ProductController {

    @Autowired
    ProductService productService;

   @GetMapping("/product-detail")
   public String home() {

      return "product/product-detail";

   }

   @GetMapping("/products")
   public String products(Model model) {
      List<Product> products = productService.getProductList();
      for (Product product : products) {
         System.out.println(product.getImageUrl()); 
      }
      //System.out.println(products);
      model.addAttribute("products", products);
      return "product/product";
   }

   
   @GetMapping("/Liverproducts")
   public String liverproducts(Model model) {
       List<Product> products = productService.getLiverList();
       System.out.println(products);
       model.addAttribute("products", products);
       return "product/product";
   }
   
   @GetMapping("/Exerciseproducts")
   public String exerciseproducts(Model model) {
       List<Product> products = productService.getExerciseList();
       model.addAttribute("products", products);
       return "product/product";
   }
   
   @GetMapping("/Brainproducts")
   public String brainproducts(Model model) {
       List<Product> products = productService.getBrainList();
       model.addAttribute("products", products);
       return "product/product";
   }
   
   @GetMapping("/Stomachproducts")
   public String stomachproducts(Model model) {
       List<Product> products = productService.getStomachList();
       model.addAttribute("products", products);
       return "product/product";
   }
   
   @GetMapping("/Eyeproducts")
   public String eyeproducts(Model model) {
       List<Product> products = productService.getEyeList();
       model.addAttribute("products", products);
       return "product/product";
   }
   
   @GetMapping("/Boneproducts")
   public String boneproducts(Model model) {
       List<Product> products = productService.getBoneList();
       model.addAttribute("products", products);
       return "product/product";
   }
   

   @GetMapping("/getImage")
   @ResponseBody   
    public ResponseEntity<byte[]>  displayFile(String imageName)throws Exception{      
     String saveDir="c://upload/";
      System.out.println("test"+imageName);
      InputStream in = null; 
      ResponseEntity<byte[]> entity = null;

      try{      
         String formatName = imageName.substring(imageName.lastIndexOf("/")+1);              
         HttpHeaders headers = new HttpHeaders();      
         in = new FileInputStream(saveDir+imageName);      

         headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);         
           
         imageName = imageName.substring(imageName.indexOf("_")+1); 
         headers.add("Content-Disposition","attachment; filename=\""+ 
               URLEncoder.encode(imageName,"utf-8").replace("+","%20")+"\"");
         entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), 
                                    headers, HttpStatus.CREATED);
      System.out.println(formatName);
      }catch(Exception e){
         e.printStackTrace();
         entity = new ResponseEntity<byte[]>(
               HttpStatus.BAD_REQUEST);
      }finally{
         in.close();
      }
      return entity;    
   }   

}