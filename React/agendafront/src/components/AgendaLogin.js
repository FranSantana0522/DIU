import React from 'react';
import './App.css'; // Importa tus estilos CSS personalizados aquí
import 'bootstrap/dist/css/bootstrap.min.css'; // Importa los estilos CSS de Bootstrap
import fotoPerfil from './images/fotoPerfil.jpg';
import './css/styles.css';
import { BrowserRouter as Router, Switch, Route, Link } from 'react-router-dom';

class AgendaLogin extends Component {
    constructor(props) {
        super(props);
    }

    render() { 
        return ( 
            <div className='container d-flex justify-content-center'>
                <input type="text" className="form-control mb-2 mr-sm-2" placeholder="Name" />
            </div>
        );
    }
}
 
export default AgendaLogin;