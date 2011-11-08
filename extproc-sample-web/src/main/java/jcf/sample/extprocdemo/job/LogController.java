package jcf.sample.extprocdemo.job;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import jcf.extproc.ExternalProcessOperator;
import jcf.extproc.fileaccess.FileAccess;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LogController {

	@Autowired
	private ExternalProcessOperator operator;

	@Autowired
	private FileAccess fileAccess;

	@RequestMapping(value="/jobs/log/{jobName}", method=RequestMethod.GET)
	public String log(Model model, @PathVariable String jobName, @RequestParam String sInstanceId) throws IOException {
		String job = jobName; // escape /
		long instanceId = Long.valueOf(sInstanceId);

		File file = fileAccess.getLogFile(operator.getJobInstanceInfo(job, instanceId));


		if(file!=null){
			model.addAttribute("logContents", FileUtils.readFileToString(file));

		}else{
			model.addAttribute("logContents", "");
		}
		model.addAttribute("jobName", jobName);
		model.addAttribute("jobInstance", sInstanceId);

		return "jobs/log";
	}
}