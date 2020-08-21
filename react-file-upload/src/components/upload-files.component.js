import React, { Component } from "react";
import UploadService from "../services/upload-files.service";
import LoadingSpinner from './LoadingSpinner';
import "./upload-files.css";
import before from './brfore.png';
import after from './after.png';
import ins from './ins.pdf'


export default class UploadFiles extends Component {
  constructor(props) {
	super(props);
	this.selectFile = this.selectFile.bind(this);
	this.upload = this.upload.bind(this);
	this.state = {
		selectedFiles: undefined,
		currentFile: undefined,
		progress: 0,
		message: "",
		id: "",
		fileInfos: [],
		loading: false
	  };
	
  	}
	selectFile(event) {
	this.setState({
	selectedFiles: event.target.files
	});
	}
	upload() {
		let currentFile = this.state.selectedFiles[0];

		this.setState({
		progress: 0,
		currentFile: currentFile,
		loading : true
		});

		UploadService.upload(currentFile, (event) => {
		this.setState({
			progress: Math.round((100 * event.loaded) / event.total)
		});
		})
		.then((response) => {
			this.setState({
			message: response.data.message,
			id: response.data.id
			});
			return UploadService.getFilesToShow(this.state.id);
		})
		.then((files) => {
			this.setState({
			fileInfos: files.data,
			loading :false
			});
		})
		.catch(() => {
			this.setState({
			progress: 0,
			message: "The file is not in the correct format",
			currentFile: undefined,
			});
		});

		this.setState({
		selectedFiles: undefined,
		});
	}


    render() {
		const {
		  selectedFiles,
		  currentFile,
		  progress,
		  message,
		  fileInfos,
		  loading
		} = this.state;
		const instructionMessage = progress === 100 ? 'for upload a new file please refresh' : progress === 0 ? 'please upload a file  in .xlsx format , File must not exceed 2Mb.' : ''
	
		return (
		  <div className="upload-file-wrapper">
			<div className="xlsx">{instructionMessage}</div>

			{currentFile && (
			  <div className="progress">
				<div
				  className="progress-bar progress-bar-info progress-bar-striped"
				  role="progressbar"
				  aria-valuenow={progress}
				  aria-valuemin="0"
				  aria-valuemax="100"
				  style={{ width: progress + "%" }}
				>
				  {progress}%
				</div>
			  </div>
			)}
			<div className="action-wrapper">
				<input type="file" onChange={this.selectFile} />
		
				<button className="btn btn-success"
				disabled={!selectedFiles}
				onClick={this.upload}
				>
				Upload
				</button>
			</div>
			<div className="alert alert-light" role="alert">
			  {message}
			</div>

			<div className='process-data'> {loading? <LoadingSpinner /> :fileInfos > 0 && 'File is ready'}</div>
			<div className="card">
			  <div className="card-header">List of output files</div>
			  <ul className="list-group list-group-flush">
				{fileInfos &&(
					<li className="list-group-item">
					  <a target={fileInfos.url}>{fileInfos.name}</a>
					</li>
				  )}
			  </ul>
			</div>
			<div className='explanation'>
				
				{(<a href={ins}>  Click to download Instructions in Hebrew</a>)}	
				</div>
				<h5 className='img-title'>
					example for input and output file			
				</h5>
				<div className='images-wrapper'>
					
					<img className='before' src={before}/>
					<img src={after}/>

			</div>
		  </div>
		);
	  }
}