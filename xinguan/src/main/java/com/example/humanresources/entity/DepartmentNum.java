package com.example.humanresources.entity;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

@Data
public class DepartmentNum implements Serializable {
    private static final long serialVersionUID = -32723474590818165L;

    private int departmentId;
    /**
     * 部门Id
     */

    private int num;
    /**
     * 部门人数
     */

}