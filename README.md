countdown-nz-deals-parser
=========================

Was looking out for options to parse contents on a website and found jSoup. 
Currently, it should parse deals from the various webpage and provide users with a the required print out on the console. 
This took me about a day to finish. Basically looking through the html code and debugging with eclipse helped out in the implementation. 
Users who know how to code, could even make use of the print writer written in the code to write out a file with all the deals in it. Or they could also improve it by working on a GUI for it. 

Additionally, I have worked with Google Drive Spreadsheet to consolidate all data retrieve over a number of weeks. 
If you are a consumer in New Zealand looking for cheap supermarket deals. 
That spreadsheet is for you. 
https://docs.google.com/spreadsheets/d/16F9t-HncTzZlzcZPb_pU1pj-qWLCJurOo_FE2yf_Piw/edit?usp=sharing

In the spreadsheet, I have included Google Script functions to pick up the latest list that I have compiled on the deals for the specific week. Google script has been a pain and took me approximately 3 weeks to tweak it to get it to work for the spreadsheet. Even now, there are still certain issue with the script and I am still looking into getting it right. 
The script would work itself to update the above mentioned spreadsheet with 2 different functions. 
One function would just update existing products a lower price, if such a case exist, and the other function is to insert new product if the product is not on the aboved mention spreadsheet. 

Please feel free to make any suggestions and I will look into it asap. 
