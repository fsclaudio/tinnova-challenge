import {Link, NavLink} from 'react-router-dom'
import './styles.scss';


const Navbar = () => (
    <nav className="row bg-primary main-nav">
        <div className="col-3" >
            <Link to="/" className="nav-logo-text">
                <h4>Challege Tinnova</h4>
            </Link>
        </div>
        <div className="col-6 offset-2">
            <ul className="main-menu">
                <li>
                    <NavLink to="/" activeClassName="active" exact>
                        Home
                    </NavLink>
                </li>
                <li>
                    <NavLink to="/veiculo" activeClassName="active">
                        Cadastro
                    </NavLink>
                </li>
            </ul>
        </div>
    </nav>
);

export default Navbar;