import React from 'react';
import './App.css'; // Importa tus estilos CSS personalizados aquí
import 'bootstrap/dist/css/bootstrap.min.css'; // Importa los estilos CSS de Bootstrap
import fotoPerfil from './images/fotoPerfil.jpg';
import './css/styles.css';
import { BrowserRouter as Router, Switch, Route, Link } from 'react-router-dom';

import AgendaList from './components/AgendaList';
import AgendaEdit from './components/AgendaEdit';
import AgendaAdd from './components/AgendaAdd';

function App() {

  return (
    <Router>
    {/*Administrador añadir editar borrar*/}
    <div className='container-fluid'>
        
      <div className='navbar navbar-expand-lg navbar-light bg-dark row'>
        <div className="col-3">
          <Link to={"/agenda"} className="navbar-brand text-light">
            Agenda
          </Link>
        </div>
        <div className="col-3">     
        <Link to={"/add"} className="navbar-brand text-light">
            Añadir persona
          </Link>
        </div>
        <div className="col-3 d-flex align-items-center">    
        <Link to={"/user"} className="navbar-brand text-light">
            Usuario
          <img src={fotoPerfil} alt="Perfil" className="img-fluid rounded-5 ms-2" style={{ maxHeight: '3vh' }}/>     
        </Link>
        </div>
        <div className="col-3">
        <Link to={"/agenda"} className="navbar-brand text-light">
            Cerrar sesion
          </Link>
        </div>
      </div>

      {/*En otro componente tabla Switch*/}
      <div className="container-fluid">
    
      <Switch>
          {/*El en switch se renderizarán todas los compoentes cuta URL coicidan con la activa*/}
            <Route exact path={["/", "/agenda"]} component={AgendaList} />
          { <Route exact path="/add" component={AgendaAdd} />}
          { <Route path="/agenda/:id" component={AgendaEdit} />}
      </Switch>
    
      {/**/}
      </div>
      
    </div>
    </Router>
  );
}

export default App;
