# GovTech TAP Technical Assessment

## Setup Instructions
1. Download `MySql` and `Postman`. 
2. Use `tap.sql` to set up the database.
3. Run `TapAssessmentApplication` to start the server.
4. Endpoints created can be viewed at https://www.getpostman.com/collections/d2bf357cad3b92ff9832
    1. Enter `Postman` and click `Import -> Import From Link` and copy the link above
    
## Assumptions
1. All input queries are valid, i.e. `spouseId` exists ONLY if `marital_status = married`, `marital_status = divorced`
means that he/she has no spouse, and `spouseId = null`.
2. Husbands and wives live together in the same household, and hence, `marital_status = married` means your spouse is 
in the same household.
3. No search parameters are provided for the grant disbursements (Question 5), instead, the criteria are accounted for 
in the methods created in the code, and results will return the recipients based on the criteria.
