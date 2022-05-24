package com.example.springd3.controll;

import com.example.springd3.model.Park;
import com.example.springd3.model.Respons;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/park")
public class ParkControll {

    ArrayList<Park> parks=new ArrayList();

    @GetMapping
    public ResponseEntity<ArrayList> getRide(){
        return ResponseEntity.status(200).body(parks);
    }

    @PostMapping
    public ResponseEntity addRide(@RequestBody @Valid Park park , Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
            parks.add(park);
            return ResponseEntity.status(200).body(new Respons("Ride added",200));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateRide(@PathVariable String id,@RequestBody @Valid Park park){

        if(park.getRideID().equals(id)) {
            parks.set(Integer.parseInt(id), park);
            return ResponseEntity.status(200).body(new Respons("update", 200));
        }
        return ResponseEntity.status(400).body(" Can't update");
    }


    @DeleteMapping("/remove/{id}")
    public ResponseEntity removeRide(@PathVariable int id,@RequestBody Park park){
        parks.remove(park);
        return ResponseEntity.status(200).body(new Respons("remove",200));
    }

    @PutMapping("/sell/{id}")
    public ResponseEntity sell(@PathVariable Integer amount,@PathVariable String id,@RequestBody  Park park){

        Integer index= parks.indexOf(id);

        if(index<=0){
            if(amount>=park.getPrice()){
                park.setTicket(park.getTicket()-1);
                return ResponseEntity.status(200).body("Ticket sell");
            }
        }
        return ResponseEntity.status(400).body(new Respons(" purchased not complete",400));
    }

    ArrayList<Integer>temp=new ArrayList<Integer>();

    @PutMapping("refill/{id}")
    public ResponseEntity refillTickets(@PathVariable String id){
        Integer index=getIndex(id);
        if(parks.get(index).getTicket()>0){
            return ResponseEntity.status(400).body("there is ticket");
        }
        parks.get(index).setTicket(temp.get(index));
        return ResponseEntity.status(200).body("ticket refilled");
    }
    public Integer getIndex(String id){
        for (int i = 0; i < this.parks.size(); i++) {
            if (parks.get(i).getRideID().equals(id)){
                return i;
            }
        }
        return null;
    }


}
