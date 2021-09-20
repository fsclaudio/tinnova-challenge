import React from 'react';
import { Link } from 'react-router-dom';
import { Veiculos } from '../../../../../core/types/Veiculos';
import './styles.scss';

type Props = {
  carro: Veiculos;
  onRemove: (veiculoId: number) => void;
}
const Card = ({carro,onRemove }: Props) =>{
    return(
        <div className= "card-base product-card-admin">
            <div className="row">
              <div className="col-2 text-center border-right border-success py-3">
              </div>
              <div className="col-7 py-3">
                <h3 className="product-card-name-admin">
                   {carro.veiculo}
                </h3>
                
                <div>
                {carro.marca}
                </div>
                <div>
                {carro.descricao}
                </div>
                <div>
               
                </div>
              </div>
              <div className="col-3 pt-3 pr-5">
                <Link 
                to={`/admin/products/${carro.id}`}
                type="button"
                className="btn btn-outline-secondary btn-block border-radius-10 mb-3 btn-edit"
                >
                  EDITAR
                </Link>
                <button 
                type="button"
                className="btn btn-outline-danger btn-block border-radius-10"
                onClick={()=> onRemove(carro.id)}
                >
                  EXCLUIR
                </button>
              </div>
            </div>
        </div>

    )
}

export default Card;