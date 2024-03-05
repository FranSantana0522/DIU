import firebase from "firebase/app";
import "firebase/auth";
import "firebase/firestore";

/*
const firebaseConfig = {
  apiKey: "AIzaSyA9WZB5N6ekNxyN3yGaUwjuBilvXItUv38",
  authDomain: "fir-auth-article.firebaseapp.com",
  databaseURL: "https://fir-auth-article.firebaseio.com",
  projectId: "fir-auth-article",
  storageBucket: "fir-auth-article.appspot.com",
  messagingSenderId: "774252759419",
  appId: "1:774252759419:web:e014ddfa3553a4832a15de",
  measurementId: "G-77Z5WJ0SET"
};
// Initialize Firebase
firebase.initializeApp(firebaseConfig);

*/
//////////////////////////////////////////////////////////



 // Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
const firebaseConfig = {
    apiKey: "AIzaSyA3iM586DrwudAD429gTPv7P3MWpa1957U",
    authDomain: "agenda-9333e.firebaseapp.com",
    projectId: "agenda-9333e",
    storageBucket: "agenda-9333e.appspot.com",
    messagingSenderId: "251659064068",
    appId: "1:251659064068:web:38ca7b9829019aac4e7651"
  };

// Initialize Firebase
const app = initializeApp(firebaseConfig);

export default app;