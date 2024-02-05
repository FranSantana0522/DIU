import React, { Component } from "react";
import TutorialDataService from "../services/tutorial.service";
import { Link } from "react-router-dom";

export default class Tutorial extends Component {
    constructor(){
        super();
        this.takeName=this.takeName.bind(this);
        this.takeDescription=this.takeDescription.bind(this);
        this.takePublished=this.takePublished.bind(this);
        this.editTutorial = this.editTutorial.bind(this);
        this.state = {
            id: '',
            titulo:'',
            descripcion:'',
            publicado:false
        };
         
    }
    componentDidMount(){
      const tutorialId = this.props.match.params.id
      this.setState({
        id: tutorialId
      });  
      console.log(tutorialId)
    }
    takeName(e){
      const title = e.target.value;
      console.log(title)
      this.setState({
        titulo: title
      });
    }
    takeDescription(e){
      const descripcion = e.target.value;
      console.log(descripcion)
      this.setState({
        descripcion: descripcion
      });
    }
    takePublished(e){
      const published = e.target.checked;
      console.log(published)
      this.setState({
        publicado: published
      });
    }
    editTutorial(){
        TutorialDataService.update(this.state.id,this.state).then(response => { 
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    }
    render(){
      const {id} = this.state;
        return(
            <div className="col-md-6">
          {/*Renderizado condicional. Si current tutorial el null se dibuja lo de abajo. Si no,*/}
          {/*se dibuja "Please click on a Tutorial..." ver más abajo.*/}
          {id ? (
            <div>
              <h4>Tutorial</h4>
              <div>
                <label>
                  <strong>Title: </strong>
                </label>
                <input type="text" className="form-control"
       placeholder="Introduce titulo" value={this.state.titulo} name="titulo" onChange={this.takeName}>
                </input>
              </div>
              <div>
                <label>
                  <strong>Description: </strong>
                </label>
                <input type="text" className="form-control" placeholder="Introduce descripcion"
                onChange={this.takeDescription} name="descripcion">
                </input>
              </div>
              <div>
                <label>
                  <strong>Status: </strong>
                </label>
                <input type="checkbox" name="publicado" onChange={this.takePublished} 
                checked={this.state.publicado}/> Publicado
              </div>
              <div>
                <button className="btn btn-outline-secondary" onClick={this.editTutorial}>Aceptar</button>
              </div>
            </div>
          ) : (
            <div>
              <br />
              <p>Error</p>
            </div>
          )}
        </div>   
            
            
            )
    }



}