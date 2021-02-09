/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author handez
 */
public class TableDTO {
    private String table_id;
    private boolean status;

    public TableDTO() {
    }

    public TableDTO(String table_id, boolean status) {
        this.table_id = table_id;
        this.status = status;
    }

    public String getTable_id() {
        return table_id;
    }

    public void setTable_id(String table_id) {
        this.table_id = table_id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
}
