// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
import {getAuth} from "firebase/auth";

// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
  apiKey: "AIzaSyDue-2N_0dqWF2zn5PlcgCPlyuGjhdf-5c",
  authDomain: "dep11-messaging-app.firebaseapp.com",
  projectId: "dep11-messaging-app",
  storageBucket: "dep11-messaging-app.appspot.com",
  messagingSenderId: "643767941887",
  appId: "1:643767941887:web:29779610513d8fca5eeaca",
  measurementId: "G-QXE4W4P1RJ"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
const auth = getAuth(app);

export {app,auth};