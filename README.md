# Model-View-ViewModel (ie MVVM):

## MVVM Best Pratice:
 1. Avoid references to Views in ViewModels.
 2. Instead of pushing data to the UI, let the UI observe changes to it.
 3. Distribute responsibilities, add a domain layer if needed.
 4. Add a data repository as the single-point entry to your data.
 5. Expose information about the state of your data using a wrapper or another LiveData.
 6. Consider edge cases, leaks and how long-running operations can affect the instances in your architecture.
 7. Don’t put logic in the ViewModel that is critical to saving clean state or related to data. Any call you make from a ViewModel can be the last one.
 
 ## Architecture Flow Diagram:
 ![Untitled Diagram drawio](https://user-images.githubusercontent.com/13237433/150957910-bf2c189d-b3c7-44d5-b69d-f19022590e1f.png)
