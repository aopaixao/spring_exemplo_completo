package com.residencia.ecommerce.services;


import com.residencia.ecommerce.entities.Endereco;
import com.residencia.ecommerce.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService {

    @Autowired
    public EnderecoRepository enderecoRepository;
//******************************************************************************************************************

    public Endereco findById(Integer id) {
        return enderecoRepository.findById(id).get();
    }
//******************************************************************************************************************

    public List<Endereco> findAll(Integer id) {
        return enderecoRepository.findAll();
    }

//******************************************************************************************************************


    public long count() {
        return enderecoRepository.count();
    }

//******************************************************************************************************************

    public Endereco save(Endereco endereco) {
        Endereco novoEndereco = enderecoRepository.save(endereco);
        if (novoEndereco.getEnderecoId() != null) {
            return novoEndereco;
        } else {
            return null;
        }

    }
//********************************************************************************************************************

    public Endereco update(Endereco endereco) {

        return enderecoRepository.save(endereco);
    }


    //   OBS      analisar necessidade.

//     public Endereco update(Integer id,Endereco novoEndereco){
//        Endereco antigoEndereco = enderecoRepository.findById(id).get();
//        updateDados (antigoEndereco,novoEndereco);
//        return enderecoRepository.save(antigoEndereco);
//     }
//     private void updateDados(Endereco antigoEndereco , Endereco novoEndereco){
//        antigoEndereco.setCep(novoEndereco.getCep());
//        antigoEndereco.setClientesByEnderecoId(novoEndereco.getClientesByEnderecoId());
//        antigoEndereco.setUf(novoEndereco.getUf());
//        antigoEndereco.setCidade(novoEndereco.getCidade());
//        antigoEndereco.setBairro(novoEndereco.getBairro());
//        antigoEndereco.setRua(novoEndereco.getRua());
//        antigoEndereco.setNumero(novoEndereco.getNumero());
//        antigoEndereco.setComplemento(novoEndereco.getComplemento());
//  }


//********************************************************************************************************************

    public boolean delete(Integer id) {
        if (id != null) {
            enderecoRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }


}
