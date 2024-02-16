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

      {/*En otro componente tabla*/}
      <div className="container">
      <div className="row">
          <div className="col">
            <table className="table table-dark table-striped caption-top table-borderless table-hover">
            <caption>Lista de personas</caption>
              <thead>
                <tr>
                  <th scope="col">ID</th>
                  <th scope="col">Nombre</th>
                  <th scope="col">Edad</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <th scope="row">1</th>
                  <td>John Doe</td>
                  <td>30</td>
                </tr>
                <tr>
                  <th scope="row">2</th>
                  <td>Jane Smith</td>
                  <td>25</td>
                </tr>
                <tr>
                  <th scope="row">3</th>
                  <td>Bob Johnson</td>
                  <td>40</td>
                </tr>
              </tbody>
            </table>
            <button className="btn btn-danger">Borrar todo</button>
          </div>
        </div>
      </div>

    </div>
  );
}

export default App;
