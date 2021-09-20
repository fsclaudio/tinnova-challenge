import React from 'react';
import { Link, Route, Switch } from 'react-router-dom';
import Form from './Form';

const Products= () => {
  return (
      <div>
          
        <Switch>
            <Route path="/admin/products" exact>
        
            </Route>
            <Route path="/admin/products/:productId">
            <Form />
            </Route>
        </Switch>
      </div>
  );
}

export default Products;