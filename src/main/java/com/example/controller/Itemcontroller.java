package com.example.controller;


import com.example.dao.Itemdao;
import com.example.model.Item;
import com.example.service.Itemservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@Controller
@Validated
public class Itemcontroller {

    private final Itemservice itemservice;


    @Autowired
    public Itemcontroller(Itemservice itemservice) {
        this.itemservice = itemservice;
    }

    @Autowired
    private Itemdao itemdao;

    // -------------------------------------------------insert--------------------------------

    @GetMapping("/addBook")
    public String getBook(Model model) {
        model.addAttribute("item", new Item());
        return "addbook";
    }


//    @PostMapping("/addBook")
//    public String addBook(@ModelAttribute("item") @Valid Item item, Errors error, Model model) {
//        String name = item.getName();
//        if (error.hasErrors()) {
//            return "addbook";
//        }
//        if (itemservice.getName(name) != null) {
//            model.addAttribute("BookForm", "This book already exists");
//            return "addbook";
//        } else {
//            itemservice.createItem(item);
//            return "redirect:/listBooks";
//        }
//    }


    @PostMapping("/addBook")
    public String addBook(@ModelAttribute("item") @Valid Item item, Errors error, Model model) {
        String name = item.getName();
        if (error.hasErrors()) {
            return "addbook";
        }
            String EmailToCheck=item.getAuthormail();
        if (itemservice.getName(name) != null) {
            if (itemservice.isEmailPresentOrNotService(EmailToCheck)) {
                model.addAttribute("BookForm", "gmail exists");
                return "addbook";
            }
            model.addAttribute("BookForm", "This book already exists");
            return "addbook";
        }
            itemservice.createItem(item);
            return "redirect:/listBooks";
        }


    //-----------------------------------FOR POSTMAN Insert-------------------------------------

    @PostMapping("/addBooks")
    @ResponseBody
    public String postItems(@RequestBody List<Item> item) {
        itemservice.postItems(item);
        return "List Inserted";
    }


    @PostMapping("/addBookPostman")
    public ResponseEntity<String> addBooksPostman(@RequestBody @Valid Item item, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder("Invalid input:\n");
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError error : fieldErrors) {
                errorMessage.append(error.getDefaultMessage()).append("\n");
            }
            return new ResponseEntity<>(errorMessage.toString(), HttpStatus.BAD_REQUEST);
        }
        String EmailToCheck = item.getAuthormail();
        if (itemservice.isEmailPresentOrNotService(EmailToCheck)) {
            return new ResponseEntity<>("Duplicate entry: Email already exists", HttpStatus.BAD_REQUEST);
        }
        itemservice.createItem(item);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }




//    @PostMapping("/addBookPostman")
//    @ResponseBody
//    public ResponseEntity<String>  crateBookByPostman(@RequestBody Item item) {
//        try {
//           if (item.getName()=="" && item.getName().matches("[a-zA-Z\\s]*$")){
//           // if (!(item.getName() != "" && item.getName().isEmpty() && item.getName().matches("[a-zA-Z\\s]*$"))) {
//                System.out.println("inside");
//                throw new NullPointerException("null value");
//            }
//        } catch (NullPointerException e) {
//            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e.getMessage());
//
//        }
//        itemservice.createItem(item);
//        return new ResponseEntity<>("inserted",HttpStatus.OK);
//    }












    //------------------------------------------READ-------------------------------------------
    @GetMapping("/getAllItems")
    @ResponseBody
    public List<Item> getAllItems() {
        return itemservice.getAllItems();
    }


    @GetMapping("/listBooks")
    public String listBooks(Model model) {
        model.addAttribute("items", itemservice.getAllItems());
        return "listbooks";
    }


    //------------------------------------FOR POSTMAN READ-----------------------------------

    @GetMapping("/getBook/{id}")
    @ResponseBody
    public ResponseEntity<Object> getABook(@PathVariable("id") Long id,Item item) {
        Item item1 = itemservice.getItemById(id,item);
        if (item1 != null) {
            if (item1.getIsExist() != 0 && item1.getIsExist() == 1) {
                return new ResponseEntity<>(item1, HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>("Book with ID " + id + " is Deleted", HttpStatus.NOT_FOUND);
        }
        return null;
    }






    //------------------------------------------UpdateBook-------------------------------------------


    @GetMapping("/updateBook")
    public String updateBook(@ModelAttribute Item item, Long id) {
        return "updatebook";
    }

    @PostMapping("/updateBook")
    public String updateItem(@ModelAttribute("bookUpdate") Item item, Long id) {
        itemservice.updateItem(id, item);
        return "redirect:/listBooks";
    }



    //-----------------------------------------FOR UPDATE POSTMAN-----------------------------

    @PutMapping("/updateBook/{id}")
    @ResponseBody
    public ResponseEntity<String> updateBook(@PathVariable Long id, @RequestBody @Valid Item item, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder("Invalid input: ");
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for(FieldError error:fieldErrors){
                errorMessage.append(error.getDefaultMessage()).append("\n");
            }
            return new ResponseEntity<>(errorMessage.toString(), HttpStatus.BAD_REQUEST);
        }
        String EmailToCheck = item.getAuthormail();
        if (itemservice.isEmailPresentOrNotService(EmailToCheck)) {
            return new ResponseEntity<>("Duplicate entry: Email already exists", HttpStatus.BAD_REQUEST);
        }
        item.setId(id);
        if(itemservice.isIdPresentOrNotService(item)){
            itemservice.updatingInPostman(id,item);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("INVALID ID", HttpStatus.NOT_FOUND);
        }
    }









    // -------------------------------------------------DELETE--------------------------------
    @GetMapping("/deleteBook")
    public String deleteBook() {
        return "deletebook";
    }

    @PostMapping("/deleteBook")
    public String deleteItem(@RequestParam Long id, Model model) {
        Item itemToDelete = new Item();
        itemToDelete.setId(id);
        if (itemservice.isIdPresentOrNotService(itemToDelete)) {
            itemservice.deleteItem(id);
            return "redirect:/listBooks";
        } else {
            model.addAttribute("deleteForm", "  Book with id :" + id + " doesn't exist ");
            return "deletebook";
        }
    }

    //---------------------------------------------FOR POSTMAN DELETE------------------------------------

    @DeleteMapping("/deleteBook/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        Item item = new Item();
        item.setId(id);
        if (itemservice.isIdPresentOrNotService(item)){
            itemservice.deleteItem(id);
            return new ResponseEntity<>("Book Deleted", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Invalid ID",HttpStatus.NOT_FOUND);
        }
    }







    //------------------------------------------FindBook-------------------------------------------

    @GetMapping("/findBookToUpdate")
    public String findbooktoupdate() {
        return "findbooktoupdate";
    }


    @PostMapping("/findBookToUpdate")
    public String findBookPage(@RequestParam Long id, Model model, Item item) {
        Item item1 = itemservice.getItemById(id, item);
        item.setId(id);
        if (itemservice.isIdPresentOrNotService(item)) {
            int a = item1.getIsExist();
            if (a == 1) {
                itemservice.updateItem(id, item);
                return "updatebook";
            } else {
                model.addAttribute("findBookForm", "Book with id " + id + " is Deleted");
                return "findbooktoupdate";
            }
        } else {
            model.addAttribute("findBookForm", "Book with id " + id + " doesn't exist");
        }
        return "findbooktoupdate";
    }


    // -------------------------------------------------Pick a BOOK--------------------------------


    @GetMapping("/drawABook")
    public String drawBookPage() {
        return "drawabook";
    }



    @PostMapping("/drawABook")
    public String picABook(@RequestParam Long id, Model model, Item item) {
        Item bookToDraw = new Item();
        bookToDraw.setId(id);
        if (itemservice.isIdPresentOrNotService(bookToDraw)) {
            Item book = itemservice.getItemById(id, item);
            if (book != null) {
                Integer b = book.getAvailablecount();
                if (b > 0) {
                    itemservice.drawingABook(id);
                    String a = book.getName();
                    model.addAttribute("drawForm", "You Got the Book  " + a);
                    return "drawabook";
                } else if (b==0){
                    model.addAttribute("drawForm", "The given Book ID is out of stock");
                    return "drawabook";
                }
            } else {
                model.addAttribute("drawForm", "The given Book ID " + id + " doesn't exist");
            }
        } else {
            model.addAttribute("drawForm", "Invalid Book ID");
        }
        return "drawabook";
    }



    //-------------------------------------------------SuccessPage--------------------------------


    @GetMapping("/success")
    public String success() {
        return "success";
    }

    //-------------------------------------------------ADD BOOK STOCK--------------------------------



    @GetMapping("/addStock")
    public String addStockPage() {
        return "addstock";
    }


    @PostMapping("/addStock")
    public String addStockBooks(@RequestParam Long id, @RequestParam Integer i, Model model) {
        itemservice.addStock(id, i);
        return "redirect:/listBooks";
    }
}

























