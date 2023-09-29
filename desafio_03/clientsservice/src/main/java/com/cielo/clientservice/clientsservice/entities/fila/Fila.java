package com.cielo.clientservice.clientsservice.entities.fila;

import com.cielo.clientservice.clientsservice.exceptions.ClientServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Fila<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(Fila.class);
    private T[] array;
    private int primeiroDaFilaIndex;
    private int ultimoDaFilaIndex;
    private int capacidade;
    private int contador;

    public Fila(T[] array){
        this.array = array;
        capacidade = array.length;
        primeiroDaFilaIndex = 0;
        ultimoDaFilaIndex = -1;
        contador = 0;
    }

    public <T>T pegarPrimeiroDaFila(){
        if (isEmpty()){
            throw ClientServiceException.filaVazia();
        }
        T primeiroDaFila = (T) array[primeiroDaFilaIndex];
        LOGGER.info("removendo primeiro da fila");
        primeiroDaFilaIndex = (primeiroDaFilaIndex+1) % capacidade;
        contador--;
        return primeiroDaFila;
    }

    public void AdicionarAFila(T item){
        ultimoDaFilaIndex = (ultimoDaFilaIndex+1)%capacidade;
        array[ultimoDaFilaIndex] = item;
        contador++;
    }
    public int size(){
        return contador;
    }
    public boolean isEmpty() {
        return size() == 0;
    }
}
