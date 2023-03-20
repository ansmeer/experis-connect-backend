package com.experis.experisconnect.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.tags.Tag;

@OpenAPIDefinition(
        info = @Info(
                title = "Experis Connect API",
                version = "1.0",
                description = "Experis Academy Case: Alumni Network",
                license = @License(name = "MIT © 2023 ansmeer, OddM91, William-vil, Ddayisme")
        ),
        tags = {
                @Tag(name = "Group", description = "All endpoints related to groups"),
                @Tag(name = "Posts", description = "All endpoints related to posts"),
                @Tag(name = "Topic", description = "All endpoints related to topics"),
                @Tag(name = "Users", description = "All endpoints related to users"),
                @Tag(name = "Get", description = "All get endpoints"),
                @Tag(name = "Post", description = "All post endpoints"),
                @Tag(name = "Put", description = "All put endpoints"),
                @Tag(name = "Delete", description = "All delete endpoints")
        }
)
public class OpenApiConfig {
}
