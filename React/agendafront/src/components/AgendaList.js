import React, { Component } from 'react';
import {BrowserRouter as Router, Link } from "react-router-dom";
import AgendaDataService from "../services/agenda.service.js";

class AgendaList extends Component {
    constructor(props){
        super(props)
        this.retrieveAgenda=this.retrieveAgenda.bind(this);
        this.refreshList=this.refreshList.bind(this);
        this.setActiveAgenda=this.setActiveAgenda.bind(this);
        this.removeAllAgenda=this.removeAllAgenda.bind(this);
        this.totalAgenda=this.totalAgenda.bind(this);

        this.state = {
            agenda: [], //lista de agenda
            currentAgenda: null, //contacto seleccionado de la lista
            currentIndex: -1,
            searchTitle: "",
            peopleAgenda: 0
          };
    }
    componentDidMount() {
        this.retrieveAgenda();
    }
    retrieveAgenda() {
        AgendaDataService.getAll()
          .then(response => {
            this.setState({
              agenda: response.data
            });
            console.log(response.data);
          })
          .catch(e => {
            console.log(e);
          });
    }
    refreshList() {
        this.retrieveAgenda();
        this.setState({
          currentAgenda: null,
          currentIndex: -1
        });
    }
    setActiveAgenda(agenda, index) {
        this.setState({
          currentAgenda: agenda,
          currentIndex: index
        });
    }
    removeAllAgenda() {
        AgendaDataService.deleteAll()
          .then(response => {
            console.log(response.data);
            this.refreshList();
          })
          .catch(e => {
            console.log(e);
          });
    }
    totalAgenda(){
      this.peopleAgenda=this.state.agenda.length
      return (this.peopleAgenda*100)/50
    }
    render() {
        const { searchTitle, agenda, currentAgenda, currentIndex, peopleAgenda } = this.state;
        return (
          <Router>
          
            <div className="row">
          <div className="col">
            <table className="table table-dark table-striped caption-top table-borderless table-hover">
            <caption>Lista de personas</caption>
              <thead>
                <tr>
                  <th scope="col">ID</th>
                  <th scope="col">Nombre</th>
                  <th scope="col">Apellidos</th>
                  <th scope='col'>Fecha nacimiento</th>
                  <th scope='col'>Direccion</th>
                  <th scope='col'>Localidad</th>
                  <th scope='col'>Edicion</th>
                </tr>
              </thead>
              <tbody>
              {agenda && agenda.map((agenda, index) => (
                <tr onClick={() => this.setActiveAgenda(agenda,index)} key={index} scope='row'>
                  <th>{agenda.id}</th>
                  <th>{agenda.nombre}</th>
                  <td>{agenda.apellidos}</td>
                  <td>{agenda.fechaNacimiento}</td>
                  <td>{agenda.direccion}</td>
                  <td>{agenda.localidad}</td>
                  <td>
                      <Link to={{pathname: `/agenda/${agenda.id}`,state: { agenda: agenda }}} className="text-decoration-none text-warning">Editar</Link>
                  </td>
                </tr>
                ))}
              </tbody>
            </table>
            <button className="btn btn-danger" onClick={this.removeAllAgenda}>Borrar todo</button>
            <div className='mt-3 progress mb-3'>
              <div className="progress-bar bg-info" role="progressbar" style={{ width: `${this.totalAgenda()}%` }} aria-valuenow="50" aria-valuemin="0" aria-valuemax="100">
                {this.totalAgenda()}%
              </div>
            </div>
          </div>
  </div>
  </Router>
        );
    }
}

export default AgendaList;