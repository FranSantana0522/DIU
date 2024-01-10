import React from 'react';

export default function Button(props){
    const { caracter, setPantalla } = props;

    const handleClick = () => {
        setPantalla(caracter);
    };

    return(    
        <button onClick={handleClick}>{caracter}</button>
    );
}