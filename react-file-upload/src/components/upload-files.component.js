import React, { Component } from "react";
import UploadService from "../services/upload-files.service";
import LoadingSpinner from './LoadingSpinner';
import "./upload-files.css";

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
			});
			return UploadService.getFiles(currentFile.name);
		})
		.then((files) => {
			console.log(files);
			this.setState({
			fileInfos: files.data,
			loading :false
			});
		})
		.catch(() => {
			this.setState({
			progress: 0,
			message: "Could not upload the file!",
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

		console.log(fileInfos);
	
		return (
		  <div className="upload-file-wrapper">
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
					  <a href={fileInfos.url}>{fileInfos.name}</a>
					</li>
				  )}
			  </ul>
			</div>
		  </div>
		);
	  }
}