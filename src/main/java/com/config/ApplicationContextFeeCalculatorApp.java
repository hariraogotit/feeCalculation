package com.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Hari Rao on 24/04/17.
 */

@Configuration
@ComponentScan(basePackages = {"com.handlers","com.validators","com.services"})
public class ApplicationContextFeeCalculatorApp {

}
