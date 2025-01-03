package ait.cohort49.shop_49.logging;

import ait.cohort49.shop_49.model.dto.ProductDTO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//AspectJ -FrameWork

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    //JoinPoint - tochka soedinenija

    @Pointcut("execution(* ait.cohort49.shop_49.service.ProductServiceImpl.save(..))")
    public void saveProductPoint(){
        //metod dlja sozdanija PointCut
    }

//    @Before("saveProductPoint()")
//    public void beforeSavingProduct(){
//        logger.info("Before saving product in class ProductServiceImpl.save was called");
//    }

    @Before("saveProductPoint()")
    public void beforeSavingProduct(JoinPoint joinPoint){
        Object[] params = joinPoint.getArgs();

        logger.info("Method saveProduct was called with Params: {}" + params[0]);
        //logger.info("Method saveProduct was called with Array: {}" + Arrays.toString(params));
    }


    @After("saveProductPoint()")
    public void afterSavingProduct(){
        logger.info("Method saveProduct FINISH its execution");
    }

//    @AfterReturning
//    public void afterReturning(Object result){}
//
//    @AfterThrowing
//    public void afterThrowing(JoinPoint joinPoint){}



    @Pointcut("execution(* ait.cohort49.shop_49.service.ProductServiceImpl.findAllActiveProducts(..))")
    public void getAllProduct(){
        //Pustoj
    }

    @Around("getAllProduct()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = null;
        try {
            logger.info("Method findAllActiveProducts called");

            //OSNAVNOJ METOD
            result = joinPoint.proceed();

            //Logirovanie posle USPESHNOGO metoda
            logger.info("Method findAllActiveProducts successfuly returnet with result: {} ", result);

            //RESULT s LOGIKOJ

            result = ((List<ProductDTO>) result).stream()
                    .filter(product -> product.getPrice().doubleValue()>1)
                    .toList();

        } catch (Throwable ex){
            logger.error("Method findAllActiveProducts failed!!! {}", ex.getMessage());
        }
        return result == null ? Collections.emptyList() : result;
    }



}
