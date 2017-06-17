package io.pileworx.hex.port.primary.config

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import java.nio.charset.StandardCharsets
import java.util.*
import javax.annotation.Resource

@Configuration
class RestContext : WebMvcConfigurerAdapter() {

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("POST", "GET", "OPTIONS",  "DELETE", "HEAD", "PUT", "PATCH")
                .allowedHeaders( "Origin", "x-requested-with", "Content-Type", "Accept", "Authorization")
                .allowCredentials(true)
                .maxAge(3600)
    }

    @Bean
    fun mappingJackson2HttpMessageConverter(halObjectMapper: ObjectMapper): MappingJackson2HttpMessageConverter {
        halObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        halObjectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
        halObjectMapper.registerModule(SimpleModule())
        halObjectMapper.registerModule(KotlinModule())
        val jsonHalConverter = MappingJackson2HttpMessageConverter()
        jsonHalConverter.supportedMediaTypes = Arrays.asList(
                MediaType("application", "hal+json", StandardCharsets.UTF_8),
                MediaType("application", "*+hal+json", StandardCharsets.UTF_8),
                MediaType("application", "json", StandardCharsets.UTF_8))
        jsonHalConverter.objectMapper = halObjectMapper
        return jsonHalConverter
    }
}