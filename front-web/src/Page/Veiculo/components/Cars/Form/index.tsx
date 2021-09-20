
import React,{useEffect} from 'react';
import { useForm } from 'react-hook-form';
import { useHistory, useParams } from 'react-router-dom';
import {toast} from 'react-toastify';
import { makeRequest } from '../../../../../core/utils/request';
import BaseForm from '../../BaseForm';
import './styles.scss';

type FormState = {
  veiculo: string;
  descricao: string;
  marca: string;
  ano: number;
  vendido: boolean;
}

type ParamsType = {
  veiculoId: string;
}

const Form = () => {
  const { register, handleSubmit, formState: { errors },setValue } = useForm<FormState>();
  const history = useHistory();
  const {veiculoId} = useParams<ParamsType>();
  const isEditing = veiculoId !=='create';
  const formTitle = isEditing ? 'Editar Produto' : 'Cadastrar Produto';

  useEffect(() => {
   if (isEditing) {
    makeRequest({ url: `/veiculos/${veiculoId}` })
    .then(response => {
      setValue('veiculo',response.data.veiculo);
      setValue('descricao',response.data.descricao);
      setValue('marca',response.data.marca);
      setValue('ano',response.data.ano);
      setValue('vendido',response.data.vendido);
    })
   }
  }, [veiculoId,isEditing,setValue]);

  const onSubmit = (data: FormState) => {

    makeRequest({ 
      url: isEditing ? `/veiculos/${veiculoId}` : '/veiculos', 
      method: isEditing ? 'PUT' :'POST', 
      data: data })
    .then(() => {
      toast.info('⭐ Veiculo salvo com Sucesso!', {
        position: "top-right",
        theme: 'colored',
        autoClose: 5000,
        });
      history.push('/veiculos');
    })
    .catch(()=>{
      toast.error(' Error ao salvar Veiculo!',{
        theme: 'colored',
      });
    })
  }

  return (
    <form onSubmit={handleSubmit(onSubmit)}>
      <BaseForm 
      title= {formTitle}>
        <div className="row">
          <div className="col-6">
            <div className= "margin-bottom30">
              <input
                {...register('veiculo', {
                  required: "Campo obrigatório",
                  minLength: {value: 5 , message: "O campo deve ter no minimo 5 caracter"},
                  maxLength: {value: 60 , message: "O campo deve ter menor que 60 caracter"}
                })}
                name="veiculo"
                type="text" className="form-control input-base"

                placeholder="nome do Veiculo"
              />
               {errors.veiculo &&(
                        <div className="invalid-feedback d-block">
                       {errors.veiculo.message}
                    </div>
                    )}
            </div>
            <div className= "margin-bottom30">
            <input
              {...register('marca', { required: "Campo obrigatório" })}
              name="marca"
              type="text" className="form-control input-base"
              placeholder="marca do veiculo"
            />
            {errors.marca &&(
                        <div className="invalid-feedback d-block">
                       {errors.marca.message}
                    </div>
                    )}
            </div>
            <div className= "margin-bottom30">
            <input
              {...register('ano', { required: "Campo obrigatório" })}
              name="ano"
              type="number" className="form-control input-base"
              placeholder="ano do Veiculo"
            />
            {errors.ano &&(
                        <div className="invalid-feedback d-block">
                       {errors.ano.message}
                    </div>
                    )}
            </div>
            <div className= "margin-bottom30">
            <input
              {...register('vendido', { required: "Campo obrigatório" })}
              name="vendido"
              type="text" className="form-control input-base"
              placeholder="Vendido Veiculo"
            />
            {errors.vendido &&(
                        <div className="invalid-feedback d-block">
                       {errors.vendido.message}
                    </div>
                    )}
            </div>
          </div>
          
          <div className="col-6">
            <textarea
              {...register('descricao', { required: "Campo obrigatório com 4 digito" })}
              name="descricao"
              placeholder="descricao"
              className="form-control input-base "
              cols={20}
              rows={10}
            />
            {errors.ano &&(
                        <div className="invalid-feedback d-block">
                       {errors.ano.message}
                    </div>
                    )}
          </div>
        </div>
      </BaseForm>
    </form>
  )
}

export default Form;
