import React from 'react';
import './App.css'; // Importa tus estilos CSS personalizados aquí
import 'bootstrap/dist/css/bootstrap.min.css'; // Importa los estilos CSS de Bootstrap
import fotoPerfil from './images/fotoPerfil.jpg';
import './css/styles.css';
import { BrowserRouter as Router, Switch, Route, Link } from 'react-router-dom';

function App() {

  return (
    //Administrador añadir editar borrar
    <div className='container-fluid'>
        
      <div className='navbar navbar-expand-lg navbar-light bg-dark row'>
        <div className="col-3">
          <a className="navbar-brand text-light" href="#home">Agenda</a>
        </div>
        <div className="col-3">     
                <a className="nav-link text-light" href="#home">Añadir persona</a>
        </div>
        <div className="col-3 d-flex align-items-center">    
                <a className="nav-link text-light me-1  " href="#link">11 caracter</a>
                <img src={fotoPerfil} alt="Perfil" href="#link" className="img-fluid rounded-5" style={{ maxHeight: '3vh' }}/>     
        </div>
        <div className="col-3">
                <a className="nav-link text-light" href="#link">Cerrar sesion</a>
        </div>
      </div>

      {/*En otro componente tabla Switch*/}
      <div className="container">
      <Switch>
          {/*El en switch se renderizarán todas los compoentes cuta URL coicidan con la activa*/}
            <Route exact path={["/", "/agenda"]} component={AgendaList} />
          {  <Route exact path="/add" component={AgendaAdd} /> }
          {  <Route path="/agenda/:id" component={AgendaEdit} /> }
          </Switch>
      {/**/}
      </div>

    </div>
  );
}

export default App;
