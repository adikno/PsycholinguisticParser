import React from "react";
import "./App.css";
import "bootstrap/dist/css/bootstrap.min.css";

import UploadFiles from "./components/upload-files.component";

function App() {
  return (
    <div>
      <div className="container" style={{ width: "600px" }}>
        <div style={{ margin: "20px" }}>
          <h4>Psycholinguistic Parser</h4>
        </div>

        <UploadFiles />
      </div>
      <footer className='about'>
            Psycholinguistic Parser
            A websidte for parsing transcripts of meetings between psychologists and clients.
            Built following the below artical:
            <a href=" https://www.researchgate.net/publication/340595443_Using_Computerized_Text_Analysis_to_Examine_Associations_Between_Linguistic_Features_and_Clients'_Distress_during_Psychotherapy"> https://www.researchgate.net/publication/340595443_Using_Computerized_Text_Analysis_to_Examine_Associations_Between_Linguistic_Features_and_Clients'_Distress_during_Psychotherapy </a>
            <br/>
            Shapira, N., Lazarus, G., Goldberg, Y., Gilboa-Schechtman, E., Tuval-Mashiach, R., Juravski, D., & Atzil-Slonim, D. (2020). 
            <br/>
            Using Computerized Text Analysis to Examine Associations Between Linguistic Features and Clients’ Distress during Psychotherapy. Journal of counseling psychology.  
            <br/>
            The website support .xlsx files only.

            File must not exceed 2Mb.
            <br/>
            Written by Michal Abramovitch and Adi Knobel as part of their BA in Computer science in Bar Ilan University.
            Guided by Natalie Shapira.
            Questions, comments and feedback are welcome at: nd1234@gmail.com.

        </footer>
    </div>

  );
}

export default App;
