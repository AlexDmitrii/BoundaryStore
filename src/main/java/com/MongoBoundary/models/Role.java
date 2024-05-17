package com.MongoBoundary.models;

import com.MongoBoundary.util.Constant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = Constant.ROLE_DB_NAME)
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    private Integer level;

    private String name;

}
