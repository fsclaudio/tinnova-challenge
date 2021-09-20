import {BrowserRouter, Switch, Route} from 'react-router-dom';
import Navbar from './core/components/Navbar';
import Home from './Page/Home';
import Carro from './Page/Veiculo';

const Routes = () => (
  <BrowserRouter>
  <Navbar/>
     <Switch>
        <Route path="/" exact>
           <Home/>
        </Route>
        <Route path="/veiculo">
           <Carro/>
        </Route>
     </Switch>
  </BrowserRouter>
);

export default Routes;