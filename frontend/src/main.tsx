import React,{useEffect,useState} from 'react';
import {createRoot} from 'react-dom/client';
import axios from 'axios';
import './style.css';

const api=axios.create({baseURL:'http://localhost:8080/api'});
api.interceptors.request.use(c=>{const t=localStorage.getItem('token'); if(t)c.headers.Authorization=`Bearer ${t}`; return c;});

function App(){
 const [products,setProducts]=useState<any[]>([]); const [email,setEmail]=useState(''); const [password,setPassword]=useState('');
 const [logged,setLogged]=useState(!!localStorage.getItem('token')); const [message,setMessage]=useState('');
 useEffect(()=>{api.get('/products').then(r=>setProducts(r.data)).catch(e=>setMessage(e.message))},[]);
 async function auth(path:string){try{const r=await api.post('/auth/'+path,{email,password});localStorage.setItem('token',r.data.token);setLogged(true);setMessage('Authenticated')}catch(e:any){setMessage(e.response?.data?.error||'Authentication failed')}}
 async function buy(id:number){try{const r=await api.post('/checkout',{productId:id,quantity:1,address:'Customer address',idempotencyKey:crypto.randomUUID()});setMessage(`Order ${r.data.id} paid successfully`)}catch(e:any){setMessage(e.response?.data?.error||'Checkout failed')}}
 return <main><h1>Banking Shop</h1>
 {!logged && <section className="auth"><input placeholder="email" value={email} onChange={e=>setEmail(e.target.value)}/><input placeholder="password" type="password" value={password} onChange={e=>setPassword(e.target.value)}/><button onClick={()=>auth('register')}>Register</button><button onClick={()=>auth('login')}>Login</button></section>}
 {logged && <button onClick={()=>{localStorage.removeItem('token');setLogged(false)}}>Logout</button>}
 <p>{message}</p><section className="grid">{products.map(p=><article key={p.id}><h2>{p.name}</h2><p>{p.description}</p><strong>₹{p.price}</strong><p>Stock: {p.stock}</p>{logged&&<button onClick={()=>buy(p.id)}>Buy now</button>}</article>)}</section>
 </main>
}
createRoot(document.getElementById('root')!).render(<App/>);
