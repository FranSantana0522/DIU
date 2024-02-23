import React, { Component } from "react";
import ProductDataService from "../services/product.service";
import { Link } from "react-router-dom";

class ProductTotal extends Component {
    constructor(props) {
        super(props);
        const {objeto}=props;
        this.retrieveProducts=this.retrieveProducts.bind(this);
        this.takeUnits=this.takeUnits.bind(this);
        this.comprobe=this.comprobe.bind(this);
        this.totalCal=this.totalCal.bind(this);
        this.state = {
            currentProducts: null,
            total:0,
            //Objeto en memoria
            stock:20,
            id:"65d85cdbeccbda0001bce181",
            name:"name3",
            brand:"marca1",
            price:2.7,
            active:true,
            unidades:0
        };
    }
    componentDidMount() {
        this.retrieveProducts();
    }
    retrieveProducts() {
        // No actualiza el currentProducts bien tampoco el setState
        ProductDataService.get()
          .then(response => {
            this.setState({
              currentProducts: response.data
            });
            console.log(response.data);
          })
          .catch(e => {
            console.log(e);
          });
    }
    takeUnits(e){
        const units = e.target.value;
        if(units<=this.stock){
            this.setState({
                unidades: units
            });
        }else{
            return true
        }
    }
    comprobe(){
        const stock= stock
        if(stock<10){
            return true
        }else if(stock>=10){
            return false
        }
    }
    totalCal(){
        // No actualiza el total con el setState
        const unidadesMul=this.state.unidades
        const precio=this.state.price
        this.setState({
            total: unidadesMul*precio
        })
        console.log(this.state.total)
    }
    render() { 
        const { currentProducts,stock,id,name,brand,price,active,total,unidades} = this.state;
        console.log(currentProducts)
        return ( 
            <div>
            {
                <div>
                    <h4>Product: {name}</h4>
                    <div className="mt-2">
                        <label>
                            <strong>Units in stock:</strong>
                        </label>{" "}
                        {stock+" "}
                        { this.comprobe==true ? 
                            <strong className="text-danger">Units low</strong>
                            :
                            <strong className="text-success">Units ok</strong>
                        }
                    </div>
                    <div className="mt-2">
                        <label>
                            <strong>Price:</strong>
                        </label>{" "}
                        {price}
                    </div>
                    <div className="mt-2">
                        <label>
                            <strong>Units to buy</strong>
                        </label>
                        <input type="text" className="form-control-md-6" id="units" placeholder="" onChange={this.takeUnits}/>
                        {unidades>stock ?
                            <label>Pasas las unidades</label>
                            :
                            <label>Unidades correctas</label>
                        }
                    </div>
                    <button  className={"btn btn-success"} onClick={this.totalCal}>Comprar</button>
                    <div className="mt-2">
                        <label>
                            <strong>Total:</strong>
                        </label>{" "}
                        {total}€
                    </div>
                </div>

            }
            </div>
         );
    }
}
 
export default ProductTotal;