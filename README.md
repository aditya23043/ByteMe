# Byte Me!
- CLI based food ordering system specifically designed for IIITD

## How to run?
- Run this command in the `src/` directory after ensuring you have `make` installed
```
make
```
> [!IMPORTANT]  
> All the icons in this program are from nerd fonts [Nerd Fonts](https://nerdfonts.com)\
> Ensure that you have installed any nerd font from [Fonts Download](https://nerdfonts.com/font-downloads)

## Unique Feature
- The dynamically changing text inside the box is a feature which I had seen in multiple CLIs in the past but just recently got to know about the `Nu Shell` project (i am using the fish shell currently). The concept is that everything in it is a data record / table and hence even basic unix commands like `ls` or `ps` are shown properly tabularized with properly labelled columns
- This made me realize that if I put important stuff inside the box, it is emphasized than other text and hence helps me control the user's attention to crucial details
- I have also implemented a common vim keybinding, 'q' which is used to quit popups (in neovim, not vim), splits, and in general, exiting a buffer.
- I have also utilized color to add contrast to the textual content of my application
- Furthermore, all errors have been shown in red color and my program waits for 0.5/1s (depending on the case) for the user to read the error and proceed

> NOTE: I intended this to be a proper dev log but due to other ongoing tasks, I was not able to fill this up as I moved along my project so, this is not just a brief summary of my dev log instead of a proper one

## Dev Log
- (Still a work in progress as of 11:14PM 05/11/24)
- as always i forgot to log my journey and im writing this when im almost at the end

## Assumptions
- Refunds will be handed back through whatever means possible at that time and will not be logged in this program just because as of now I do not want to create a new functionality for wa wallet system since it is nearing submission and it was not explicitly mentioned to do so in the PDF
    - And hence, im just printing out a message to the admin regarding the amount to be refunded to the customer instead of directly adding that amount to the customer's "non-existent" (as of now) wallet

## Extras
- Unlike the previous assignment (github.com/aditya23043/loginCLI), I have not handled ALL exceptions, specifically the InputMismatch ones when taking int input
    - It wasnt needed but I am still mentioning it
