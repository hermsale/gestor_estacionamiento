package com.example.gestorestacionamientofx.DAO;

import com.example.gestorestacionamientofx.Model.TipoContrato;

import java.util.List;

public class TipoContratoDAO {
//    traigo todos los contratos almacenados en la BD
    List<TipoContrato> listarTipoContrato;

    TipoContrato buscarPorNombre(String nombre);
}
