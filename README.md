# ScrumAssignment
Run the project:
-----------------
./mvnw clean package
docker build --build-arg name=scrumceremony--pull -t scrumceremony:0.0.1 .

navigate inside the resouce folder and run:
docker-compose up


Endpoints:
---------
1.http://localhost:9093/api/retrospective/addRetrospective

{
"name":"data10",
"summary":"data",
"date":"12/12/2025",
"participants": ["Viktor", "Gareth", "Mike"],
"feedback": []
} 
2.http://localhost:9093/api/retrospective/saveFeedBacks

{
"name":"Data3",
"feedbackBody":"Too many items piled up in the awaiting QA",
"feedbackType":"Negative",
"retrospectiveId":"6580a65345ccb70a9a5907cc"
}

3.http://localhost:9093/api/retrospective/updateFeedBack/{feedbackid}

{
"name":"Data3",
"feedbackBody":"Updated body",
"feedbackType":"Positive",
"retrospectiveId":"6580a65345ccb70a9a5907cc",
"_id":"6580a67445ccb70a9a5907ce"
}

4.http://localhost:9093/api/retrospective/searchWithDate/:date

date="12-12-2025"

5.http://localhost:9093/api/retrospective/pagination/{pageNumber}/{pageSize}
6.http://localhost:9093/api/retrospective/getAllRetrospectives
