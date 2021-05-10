const {spawn} = require('child_process');

function exec(serviceName, command) {

    console.log(`Starting service [${serviceName}]`);

    let cmd = spawn(command, [], {cwd: './' + serviceName, shell: true});
    cmd.stdout.on('data', function (data) {
        process.stdout.write(`[${serviceName}] ${data}`);
    });

    cmd.stderr.on('data', function (data) {
        process.stderr.write(`[${serviceName}] ${data}`);
    });
}

exec('services/api-gateway', 'mvn spring-boot:run');
exec('services/customers', 'mvn spring-boot:run');
exec('services/notifications', 'mvn spring-boot:run');
exec('services/orders', 'mvn spring-boot:run');
exec('services/products', 'mvn spring-boot:run');
