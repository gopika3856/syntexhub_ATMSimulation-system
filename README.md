# syntexhub_ATMSimulation-system
Created an Account class to hold account data (account number, PIN, balance) and methods (checkBalance, deposit, withdraw, verifyPin).
Created an ATM (main) class that: asks for PIN and verifies (limit attempts),
shows a menu (Balance / Deposit / Withdraw / Exit) in a loop while session active,
updates the Account object and prints confirmations,
maintains session state and transitions.
Keep UI in console (Scanner) so it’s easy for beginners.
Add basic validation (no negative deposits, cannot withdraw more than balance).
