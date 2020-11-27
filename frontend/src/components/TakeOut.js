import React, {useState} from 'react';
import {Button, Modal,Alert} from "react-bootstrap";
import TestFoods from "../testApi/foods.json";
import "./TakeOut.css";

const TakeOutOrder=({tableId})=>{
    const [show,setShow]=useState(false);
    const [tableEmpty,setTableEmpty]=useState(true);
    const [orderContents,setOrderContents]=useState([]);
    const [addedContents,setAddedContents]=useState([]);
    const [totalPrice,setPrice]=useState(0);
    const [addedPrice,setAddedPrice]=useState(0);

    const [showOrderAlert,setOrderAlert]=useState(false);
    const [showPayAlert,setPayAlert]=useState(false);
    const [showCancleAlert,setCancleAlert]=useState(false);
    const [showAddAlert,setAddAlert]=useState(false);

    const autoOrderAlertRM=()=>{
        setTimeout(()=>{
            setOrderAlert(false);
        },1500);
     };
 
     const autoPayAlertRM=()=>{
         setTimeout(()=>{
             setPayAlert(false);
           },1500);
     };
 
     const autoAddAlertRM=()=>{
         setTimeout(()=>{
             setAddAlert(false);
           },1500);
     };
 
     const afterOrder=()=>{
         setOrderContents(addedContents);
         setAddedContents([]);
         setTableEmpty(false);
     }
  
 
     const afterPay=()=>{
         setTimeout(()=>{
             setOrderContents([]);
             setAddedContents([]);
             setTableEmpty(true);
             setPrice(0);
             setShow(false);
         },1500)
     };
 
     function handleHide(){setShow(false);};
     function handleShow(){setShow(true);};
     function resetOrder(){
         setOrderContents([]);
         setAddedContents([]);
         setTableEmpty(true);
         setPrice(0);
         setAddedPrice(0);
         setCancleAlert(false);
     }
     return(
        <span id="aTable">
         <Button id="takeOutBtn" onClick={handleShow}>테이크아웃<br></br></Button>

         <Modal size="lg" show={show} onHide={()=>{handleHide(); setCancleAlert(false); setAddedContents([]); setAddedPrice(0)}}>
         <Modal.Header closeButton>
         <Modal.Title><b>테이크아웃</b></Modal.Title>
        </Modal.Header>

        <Modal.Body>
         <div id="modalContent">
         <div className="selectedFoods" style={{float:"left",width:"45%",border:"2px solid",borderRadius:"10px",flex:"1"}}>
           <h2 style={{textAlign:"center",borderBottom:"1px solid"}}>주문 리스트</h2>
           {tableEmpty===true?(
               <div>
                     {addedContents.map(food=>(
                  <div key={Math.random()} id={food.id} style={{textAlign:"center"}}>
                  <b style={{color:"#668d3c"}}>{food.name} / {food.price}원<Button id="deleteFromAdd" onClick={()=>{
                      setAddedContents(addedContents.filter(cur=>cur.key!==food.key));
                      setAddedPrice(addedPrice-food.price);
                  }}>X</Button></b><br></br>
                  </div>
              ))}
               </div>
           ):(
               <div>
                   {orderContents.map(food=>(
                  <div key={Math.random()} id={food.id} style={{textAlign:"center"}}>
                  <b>{food.name} / {food.price}원</b><br></br>
                  </div>
              ))}

                  {addedContents.map(food=>(     
                  <div key={Math.random()} id={food.id} style={{textAlign:"center"}}>
                  <b style={{color:"#668d3c"}}>{food.name} / {food.price}원<Button id="deleteFromAdd" onClick={()=>{
                      setAddedContents(addedContents.filter(cur=>cur.key!==food.key));
                      setAddedPrice(addedPrice-food.price);
                  }}>X</Button></b><br></br>
                  </div>
              ))}
               </div>
               
           )}
              <div id="total" style={{textAlign:"center",float:"bottom"}}>
                  <b>합계: {tableEmpty===true?(addedPrice):(totalPrice+addedPrice)}원</b><br></br>
             </div>
         </div>
         <div className="servingFoods" style={{float:"right",width:"50%",border:"2px solid",borderRadius:"10px"}}>
             <h2 style={{textAlign:"center",borderBottom:"1px solid"}}>메뉴</h2>
             <div style={{margin:"8px",textAlign:"center",position:"relative"}}>
             {TestFoods.foods.map(food=>(
                 <button key={Math.random()} id={food.id} style={{backgroundColor:"white",border:"1px solid #C6C6C6"}} onClick={()=>{
                     setAddedContents(addedContents.concat({
                         key:Math.random(),
                         id:food.id,
                         name:food.name,
                         price:food.price
                     }));
                    setAddedPrice(addedPrice+food.price);
                 }}>
                 <img id="foodImg" src={food.foodImgs[0]} alt={food.id}></img><br></br>
                 <b>{food.name}</b><br></br><label>{food.price}원</label>
                 </button>
             ))}
             </div>
         </div>
         </div>
        </Modal.Body>

        <Modal.Footer id="modal-foot">
            <div style={{float:"right"}}>
                  {tableEmpty===false?(
                  <Button variant="secondary" onClick={()=>{
                  setCancleAlert(true);
                  }} style={{height:"50px", marginRight:"5px"}}>cancle</Button>):(<></>)}
                  

               {tableEmpty?((<Button variant="primary" style={{height:"50px"}} onClick={()=>{
                   if(addedContents.length===0){
                       alert("선택된 음식이 없습니다");
                   }
                   else{
                    setPrice(addedPrice);
                    setAddedPrice(0);
                    afterOrder();
                    setOrderAlert(true);
                    autoOrderAlertRM();
                   }
            }}>Order Complete</Button>)):(<></>)}

            {tableEmpty===false&&addedContents.length!==0?(<>
                <Button variant='info' style={{height:"50px",marginRight:"5px"}} onClick={()=>{
                if(addedContents.length===0){
                    alert("추가된 음식이 없습니다");
                }else{
                setOrderContents(orderContents.concat(addedContents));
                setPrice(totalPrice+addedPrice);
                setAddedContents([]);
                setAddedPrice(0);
                setAddAlert(true);
                autoAddAlertRM();}
            }}>add</Button> 
            </>):(<></>)}

            {!tableEmpty&&addedContents.length===0?(<Button variant="danger" onClick={()=>{
                setPayAlert(true);
                afterPay();
                autoPayAlertRM();
            }} style={{height:"50px"}}>Pay</Button>):(<></>)}
            </div>
            <div style={{float:"left"}}>
             <Alert show={showCancleAlert} variant="danger"><b>주문을 삭제하시겠습니까? <Button variant="danger" style={{marginRight:"5px",
             borderRadius:"10px"}}
             onClick={()=>{
                handleHide();
                resetOrder();
             }}>O</Button><Button style={{ borderRadius:"10px"}} variant="danger" onClick={()=>{
                 setCancleAlert(false);
             }}>X</Button></b></Alert>
             <Alert show={showOrderAlert} variant="success"><b>주문 완료!</b></Alert>
             <Alert show={showPayAlert} variant="success"><b>결제 완료!</b></Alert>
             <Alert show={showAddAlert} variant="success"><b>추가 완료!</b></Alert>
       </div>
        </Modal.Footer>
       </Modal>      
        </span>
    );
}

export default TakeOutOrder;