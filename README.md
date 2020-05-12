# Retrofit2 With JSONPlaceHolder
### Description:
   Using Retrofit2 Library for featch data from JSONPlaceHolder site, 
     JSONPlaceHolder is website provide API link for Access the data it's contains.
  
  Tring build Apps with **Retrofit2** and **JSON API** more time, because of improve my skills in __Android Development__.    

1. Using __Retrofit2__ Library with **JSONPlaceHolder** Site.
      - [JSONPlaceHolder Site](https://jsonplaceholder.typicode.com "JSONPlaceHolder Site")
2. Using `@GET` of **Retrofit2** with different **`Rettofit2.Call`** like:
      - `@Path`
      - `@Query`
      - `@QueryMap`
      - `@Url`

3. Libraries i used in this App:      
      - [Retrofit2 Library](https://square.github.io/retrofit "Retrofit2 Library")
      - [ButterKnife Library](https://jakewharton.github.io/butterknife "ButterKnife Library")
      - [GSON Library](https://github.com/google/gson "GSON Library")
      - [Picasso Library](https://square.github.io/picasso "Picasso Library")
      - [Recyclerview Library](https://developer.android.com/guide/topics/ui/layout/recyclerview "Recyclerview Library")
      - [Cardview Library](https://developer.android.com/guide/topics/ui/layout/cardview "Cardview Library")
4. **Features** :
      - [**Lambda Expression**](#Java-Lambda-Expression)
      - [**Check Internet Connection**](#Check-Internet-Connection-by-used:)
      - [**Transition Manager**](#Transition-Manager)
      - [**EditText Validation**](#EditText-Validation)
      
      
### Lambda Expression: 
  - [Java Lambda Expression](https://android.jlelse.eu/lambda-expressions-explained-283737e87ee0 "Java Lambda Expression")
      ```java 
            /* Before Use Lambda Expression */
            private void onClick() {
               mBtn.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v1) {
                 }
              }
            }

            /* After Use Lambda Expression */
            private void onClick() {
               mBtn.setOnClickListener(v1 -> {
               });
            }  
    ```        
      
### **Check Internet Connection** 
   by used: [Connectivity Manager](https://developer.android.com/training/monitoring-device-state/connectivity-status-type "Connectivity Manager")
 ```java
       /* Check Internet Connection method */
           public boolean isConnected() {
               boolean connected = false;
               try {
                   ConnectivityManager cm = (ConnectivityManager)
                   getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                   NetworkInfo networkInfo = cm.getActiveNetworkInfo();
                   connected = networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected();
                   return connected;
               } catch (Exception e) {
                   Log.e("Connectivity Exception ", e.getMessage());
               }
               return connected;
           }

     /* Invoke isConnection() method in onClick() method */
       private void onClick() {
         view.setOnClickListener(v -> {
             if (isConnected()) {
                     Toast.makeText(this, "Internet Connection", Toast.LENGTH_SHORT).show();
                 } else {
                     Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
                   }
         });
       }
   ```
### **Transition Manager**
  for `EditText` Visible Animation like below:
 ```java
      /* Invoke beginDelayedTransition() method */
         private void beginDelayedTransition() {
             TransitionManager.beginDelayedTransition(viewGroup);
         }

      /* Invoke beginDelayedTransition() in onClick() method */
          private void onClick() {
            button.setOnClickListener(v -> {
                beginDelayedTransition();
                mEt.setVisibility(mEt.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
               });
            }
   ```      
### **EditText Validation**:
   ```java
        private boolean validateEditText() {
            String input = edittext.getText().toString().trim();
              if (input.isEmpty()) {
                  edittext.setError("Field is Required!");
                  return false;
              } else if (Integer.parseInt(input) > 10 || Integer.parseInt(input) < 1) {
                  edittext.setError("Value Most More than Zero OR Less than 10");
                  return false;
              } else {
                  edittext.setError(null);
                  return true;
              }
          }

        /* Invoke validateEditText() method in onClick() method */
            private void onClick() {
               button.setOnClickListener(v -> {
                     if (validateEditText()) {
                           String getText = editText.getText().toString().trim();
                           Toast.makeText(this, "EditText Input is : " + getText, Toast.LENGTH_SHORT).show();
                     } else {
                             Toast.makeText(this, "No Value!", Toast.LENGTH_SHORT).show();
                      }
                 }
            }
   ```
