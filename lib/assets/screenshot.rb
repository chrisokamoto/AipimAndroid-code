require 'calabash-android/management/adb'
require 'calabash-android/operations'
%x[rm -r aipim4J/screenshots/*]

Before do |scenario|
	@ScenarioTitle = scenario.title
	@ScenarioDescription = scenario.description
	@FeatureFile = scenario.file.to_s.split('/')
	@FeatureFile.delete_at(0)
	@FeatureFile = @FeatureFile.join('/')
	@FeatureName = scenario.feature.title
	@ScenarioTags = scenario.source_tag_names

	#page.driver.browser.manage.window.maximize
	#page.driver.browser.manage.window.resize_to(1366, 768)
end

After do
	config = YAML.load_file("../aipim.yml")
	
	if (@ScenarioTags.include?('@screenshot') && @ScenarioTags.include?('@javascript') && config['screenshot'])
		path = "../aipim4J/screenshots/#{@FeatureFile}/"
		system("mkdir -p #{path}")

		sleep(1.0)
		screenshot({:prefix => "#{path}", :name=>"#{Time.now.to_i}.png"})
	end
end
