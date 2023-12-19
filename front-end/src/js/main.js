import $ from 'jquery';
import { GoogleAuthProvider, onAuthStateChanged, signInWithPopup } from "firebase/auth";
import {auth} from "../firebase";
import process from "../../dist/index.18dbc454";


const btnMemberselm = ('#members');
const { API_BASE_URL } = process.env;
// alert(API_BASE_URL)

let flag = true;
$('#btn-members').on('click',()=>{
    // alert("okay");
    
    if($('#members').hasClass('d-none')){
        $('#btn-members').removeClass('btn-outline-success');
        $('#btn-members').addClass('btn-success');
        $('#members').removeClass('d-none');
    }else{
        $('#btn-members').removeClass('btn-success');
        $('#btn-members').addClass('btn-outline-success');
        $('#members').addClass('d-none');
    }

})
function show(){
    this.flag = !this.flag;
    return flag;
}

//sign in
const user = {
    name:String,
    email:String,
    picture:String
}
const provider = new GoogleAuthProvider();
$('#sign-in').on('click',()=>{
    signInWithPopup(auth,provider).then(res=>{
        user.name=res.user.displayName;
        user.email=res.user.email;
        user.picture=res.user.photoURL;
        $('#signinLayout').addClass('d-none');
        $('main').removeClass('d-none');
        console.log(user.name,user.email,user.picture)
    }).catch(err=> alert(err));
})

onAuthStateChanged(auth,(loggedUser)=>{
    if(loggedUser){
        user.name=loggedUser.displayName.split(" ")[0];
        user.email=loggedUser.email;
        user.picture=loggedUser.photoURL;
        $('#signinLayout').addClass('d-none');
        $('main').removeClass('d-none');
        $('#logged-user').css("background-image", "url(" + user.picture + ")");
        
    }else{
        user.name = null;
        user.email = null;
        user.picture = null;
        $('#signinLayout').removeClass('d-none');
        $('main').addClass('d-none');
    }
})
const textElm = $('#text');
const btnSendElm = $('#send');
btnSendElm.on('click',()=>{
    const message = textElm.val();
    console.log(user.email,message)
    const msgObj = {
        email:user.email,
        message
    }
    displayMessage(msgObj)
    // alert(textElm.val())
})

function displayMessage({email,message}){
    const divElement = document.createElement('div');
    if (user.email ===email ){
        divElement.classList.add('msg-body', 'd-flex', 'mx-3','my-2','justify-content-end');
        divElement.innerHTML = `
                                <div class="message me p-3 border rounded-3">${message}</div>
                                `;
        $('#output-wrapper').append(divElement);
    }else {
        divElement.classList.add('msg-body', 'd-flex', 'mx-3','my-2');
        divElement.innerHTML = `
                                <div class="message other p-3 border rounded-3">${message}</div>
                                `;
        $('#output-wrapper').append(divElement);
    }

}

