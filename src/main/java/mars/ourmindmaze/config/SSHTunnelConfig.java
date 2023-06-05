package mars.ourmindmaze.config;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.File;

@Configuration
@ConditionalOnProperty("ssh.tunnel.enabled")
public class SSHTunnelConfig {

    @Value("${ssh.tunnel.host}")
    private String sshHost;

    @Value("${ssh.tunnel.port}")
    private int sshPort;

    @Value("${ssh.tunnel.username}")
    private String sshUsername;

    @Value("${ssh.tunnel.privateKeyPath}")
    private String sshPrivateKeyPath;

    @Value("${ssh.tunnel.localPort}")
    private int localPort;

    @Value("${ssh.tunnel.dbHost}")
    private String dbHost;

    @Value("${ssh.tunnel.dbPort}")
    private int dbPort;
    @PostConstruct
    public void init() throws JSchException {
        JSch jSch = new JSch();
        jSch.addIdentity(new File(sshPrivateKeyPath).getAbsolutePath());
        Session session = jSch.getSession(sshUsername, sshHost, sshPort);
        session.setConfig("StrictHostKeyChecking", "no");
        session.connect();
        session.setPortForwardingL(localPort, dbHost, dbPort);
    }
}