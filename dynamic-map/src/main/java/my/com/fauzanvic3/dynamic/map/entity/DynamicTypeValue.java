/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.com.fauzanvic3.dynamic.map.entity;

/**
 *
 * @author Fauzan
 */
public class DynamicTypeValue{
    
    private Object value;
        
    public DynamicTypeValue(Object value){
        this.value = value;
    }
    
    public <T> T getValue(Class<T> dataType){
        return dataType.cast(this.value);
    }
}