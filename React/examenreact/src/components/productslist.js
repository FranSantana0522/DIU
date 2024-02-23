import React, { Component } from "react";
import ProductDataService from "../services/product.service";
import { Link, Route,BrowserRouter as Router, Switch } from "react-router-dom";
import ProductTotal from "./producttotal";

class ProductList extends Component {
    constructor(props) {
        super(props);
        this.retrieveProducts=this.retrieveProducts.bind(this);
        this.refreshList=this.refreshList.bind(this);
        this.setActiveProduct=this.setActiveProduct.bind(this);
        this.state = {
            products: [], 
            currentProducts: null,
          };
    }
    componentDidMount() {
        this.retrieveProducts();
    }
    retrieveProducts() {
        ProductDataService.getAll()
          .then(response => {
            this.setState({
              products: response.data
            });
            console.log(response.data);
          })
          .catch(e => {
            console.log(e);
          });
    }
    refreshList() {
        this.retrieveProducts();
        this.setState({
          currentProducts: null,
        });
    }
    setActiveProduct(product) {
        console.log(product)
        if(product==null){
            console.log("Es nulo")
        }else{
            this.setState({ 
                currentProducts: product
            });
            console.log(this.state.currentProducts);
        }
        //<Link to={{pathname:`/products/${this.state.currentProducts.id}`, state: {id: this.state.currentProducts.id}}}/>
    }
    pasaProps(){

    }
    render() { 
        const { products, currentProducts } = this.state;
        return ( 
        <div className="list row">
            <div className="col-md-8 mt-3">
          <table className="table table-dark table-striped caption-top table-borderless table-hover">
            <caption>Product's list</caption>
              <thead>
                <tr>
                  <th scope="col">Name</th>
                  <th scope="col">Price</th>
                </tr>
              </thead>
              <tbody>
              {products && products.map((product, index) => (
                <tr onClick={() => this.setActiveProduct(product)} key={index} scope='row'>
                  <th>{product.name}</th>
                  <th>{product.price}</th>
                </tr>
                ))}
              </tbody>
            </table>
            </div>
            <div className="col-md-4 mt-3">
                {
                    // El currentProducts sale nulo si intento pasar el objeto al componente
                    // ProductTotal el objeto esta en memoria escrito
                    <ProductTotal></ProductTotal>
                //currentProducts ?  
                    
                    //<ProductTotal setActiveProduct={currentProducts}></ProductTotal>
                    //<Router>
                    //    <Switch>
                    //        <Route path={`/products/:id`} component={ProductTotal}/>
                    //    </Switch>
                    //</Router>
                    //:
                    //<div>
                    //    <h4>Click in a product</h4>
                    //</div> 
                }
                
            </div>
        </div>
        );
    }
}
 
export default ProductList;